package service;

import entity.*;
import entity.SurovinaNaSklade.SurovinaNaSkladeId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import util.Utils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class PersistenceManager {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public static List<SurovinaNaSklade> getAllSurovinyNasklade(){
        Session session = getSession();
        session.beginTransaction();

        Sklad sklad = getDefaultSklad();

        Query query = session.createQuery("from SurovinaNaSklade where sklad = :sklad");
        query.setParameter("sklad", sklad.getId(), StandardBasicTypes.INTEGER);
        List<SurovinaNaSklade> list = query.list();

        session.getTransaction().commit();
        return list;
    }

    public static List<Recept> getAllRecepty(){
        Session session = getSession();

        session.beginTransaction();
        List<Recept> list = session.createQuery("from Recept ").list();

        // naplneni proxy objektu kvuli lazy inicializaci
        for (Recept recept : list) {
            for (SurovinaVReceptu svr : recept.getSuroviny()) {
                svr.getSurovina().getNazev();
            }
        }

        session.getTransaction().commit();
        return list;
    }

    /**
     * Počítá se, že v db bude jediný hlavní sklad. Vytáhneme proto jen první a s ním se bude vždy pracovat
     * Metoda předdpokládá, že je již otevřena transakce
     * @return
     */
    private  static Sklad getDefaultSklad(){
        Session session = getSession();

        Query query = session.createQuery("FROM Sklad ");
        query.setMaxResults(1);
        return (Sklad) query.getSingleResult();
    }

    /**
     * Vrátí seznam všech jídel pro zadané datum
     * @param date
     * @return
     */
    public static List<Jidlo> getAllJidloForDate(Date date) {
        Date from = Utils.getDateShiftedOfDays(date, -1);
        Date to = Utils.getDateShiftedOfDays(date, 0);

        Session session = getSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Jidlo> criteria = builder.createQuery(Jidlo.class);
        Root<Jidlo> root = criteria.from(Jidlo.class);

        criteria.select(root).where(builder.and(
                builder.greaterThanOrEqualTo(root.<Date>get("datum"), from),
                builder.lessThanOrEqualTo(root.<Date>get("datum"), to)
        ));

        TypedQuery<Jidlo> query = session.createQuery(criteria);
        List<Jidlo> list = query.getResultList();
        session.getTransaction().commit();

        return list;
    }

    /**
     * Smaže recept včetně všech navázaných jídel
     * @param recept
     */
    public static void deleteRecept(Recept recept) {
        Session session = getSession();
        session.beginTransaction();

        Query query = session.createQuery("from Jidlo where recept.id = :recept");
        query.setParameter("recept", recept.getId(), StandardBasicTypes.INTEGER);
        List<Jidlo> list = query.list();

        recept = session.load(Recept.class, recept.getId());
        Sklad sklad = getDefaultSklad();

        // smazu veskera jidla
        for (Jidlo jidlo : list) {
            for (SurovinaVReceptu svr : recept.getSuroviny()) {

                // vytazeni vazebniho objektu o mnozstvi suroviny na sklade
                SurovinaNaSklade sns = getSurovinaNaSklade(svr.getSurovina(), sklad);

                // zmenseni skladovych zasob o mnozstvi potrebne k vytvoreni jidla
                sns.setMnozstvi( sns.getMnozstvi() + jidlo.getPocetPorci() * svr.getMnozstvi() );

                session.saveOrUpdate(sns);
            }
            session.delete(jidlo);
        }

        session.delete(recept);
        session.getTransaction().commit();
    }

    /**
     * Vrati true, pokud jsou ze zadaneho receptu vytvorena nejaka jidla
     * @param recept
     * @return
     */
    public static boolean isExistJidloFromRecept(Recept recept){
        Session session = getSession();
        session.beginTransaction();

        Query query = session.createQuery("select count(*) from Jidlo where recept.id = :recept");
        query.setParameter("recept", recept.getId(), StandardBasicTypes.INTEGER);
        Long count = (Long)query.uniqueResult();

        session.getTransaction().commit();
        return count > 0;
    }

    public static void saveSurovinaNaSklade(SurovinaNaSklade sns) {
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(sns);
        session.getTransaction().commit();
    }

    public static void saveRecept(Recept recept) {
        Session session = getSession();

        session.beginTransaction();
        session.saveOrUpdate(recept);
        session.getTransaction().commit();
    }

    /**
     * Zjistí, zda jsou na skladě dostupné suroviny pro zadaný počet porcí jídla z receptu.
     * Vrátí null, pokud jsou dostatečné zásoby. Jinak vrací seznam chybějících surovin
     * @param recept
     * @param pocetPorci
     * @return
     */
    public static String isSurovinyAvilableForNumberOfRecept(Recept recept, int pocetPorci) {
        Session session = getSession();
        session.beginTransaction();

        StringJoiner joiner = new StringJoiner(", ");
        boolean result = true;

        // nacteni objektu do session kvuli lazy
        recept = session.load(Recept.class, recept.getId());

        Sklad sklad = getDefaultSklad();

        for (SurovinaVReceptu s : recept.getSuroviny()) {

            int needed = pocetPorci * s.getMnozstvi();
            SurovinaNaSklade sns = getSurovinaNaSklade(s.getSurovina(), sklad);

            // pokud neni na sklade dostatek surovin, zapise se do navratoveho stringu
            if (sns.getMnozstvi() < needed){
                joiner.add(sns.getSurovina().getNazev() + " (" + (needed - sns.getMnozstvi())+ ")");
                result = false;
            }
        }

        session.getTransaction().commit();

        if (result){
            return null;
        } else {
            return joiner.toString();
        }
    }

    public static void saveJidlo(Jidlo jidlo){
        saveJidlo(jidlo, 0);
    }

    public static void updateJidlo(Jidlo jidlo, int pocetPorciDiff){
        saveJidlo(jidlo, pocetPorciDiff);
    }

    /**
     * Privátní společná metoda pro save i update jídla
     * @param jidlo
     * @param pocetPorciDiff - rozdíl v počtu porcí oproti db hodnotě
     */
    private static void saveJidlo(Jidlo jidlo, int pocetPorciDiff){
        Session session = getSession();
        session.beginTransaction();

        Sklad sklad = getDefaultSklad();

        // nacteni objektu do session kvuli lazy
        Recept recept = session.load(Recept.class, jidlo.getRecept().getId());

        for (SurovinaVReceptu svr : recept.getSuroviny()) {

            SurovinaNaSklade sns = getSurovinaNaSklade(svr.getSurovina(), sklad);

            // zmenseni skaldovych zasob o mnozstvi potrebne k vytvoreni jidla
            sns.setMnozstvi( sns.getMnozstvi() - pocetPorciDiff * svr.getMnozstvi() );

            session.saveOrUpdate(sns);
        }

        session.saveOrUpdate(jidlo);
        session.getTransaction().commit();
    }

    /**
     * Smaže jídlo a v odpovídajícím množství opět navýší skladové zásoby
     * @param jidlo
     */
    public static void deleteJidlo(Jidlo jidlo){
        Session session = getSession();
        session.beginTransaction();

        Sklad sklad = getDefaultSklad();

        // nacteni objektu do session kvuli lazy
        Recept recept = session.load(Recept.class, jidlo.getRecept().getId());

        for (SurovinaVReceptu svr : recept.getSuroviny()) {

            // vytazeni vazebniho objektu o mnozstvi suroviny na sklade
            SurovinaNaSklade sns = getSurovinaNaSklade(svr.getSurovina(), sklad);

            // zmenseni skladovych zasob o mnozstvi potrebne k vytvoreni jidla
            sns.setMnozstvi( sns.getMnozstvi() + jidlo.getPocetPorci() * svr.getMnozstvi() );

            session.saveOrUpdate(sns);
        }

        session.delete(jidlo);

        session.getTransaction().commit();
    }

    /**
     * Vrati zaznam SNS. Pocita s tim, ze je jiz otevrena session!!!
     * @param surovina
     * @param sklad
     * @return
     */
    private static SurovinaNaSklade getSurovinaNaSklade(Surovina surovina, Sklad sklad){
        SurovinaNaSkladeId id = new SurovinaNaSkladeId(surovina.getId(), sklad.getId());
        Session session = getSession();
        return session.get(SurovinaNaSklade.class, id);
    }

    /**
     * Vrací seznam všech existujících surovin
     * @return
     */
    public static List<Surovina> getAllSuroviny() {
        Session session = getSession();
        session.beginTransaction();
        List<Surovina> list = session.createQuery("from Surovina ").list();
        session.getTransaction().commit();
        return list;
    }

}
