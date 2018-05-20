import entity.Jidlo;
import entity.Recept;
import entity.Sklad;
import entity.Surovina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;


public class Main {

    /**
     * Metoda pro test persistence dat
     * @param args
     */
    public static void main(String[] args) {

        SessionFactory sessionsFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionsFactory.openSession();


        Sklad sklad = new Sklad("skladka", "za rohem", 814);

        Surovina mouka = new Surovina("mouka","gram");
        Surovina drozdi = new Surovina("drozdi","gram");
        Surovina voda = new Surovina("voda","litr");
        Surovina pivo = new Surovina("pivo","litr");

        Recept bramboracka = new Recept("Bramboracka","Uvařit v hrnci",50, 99);

        Jidlo obed = new Jidlo(new Date(), 99, bramboracka);

        sklad.addSurovina(mouka, 200);
        sklad.addSurovina(drozdi, 5);
        sklad.addSurovina(voda, 500);
        sklad.addSurovina(pivo, 120);

        bramboracka.addSurovina(voda, 20);
        bramboracka.addSurovina(pivo, 30);


        session.beginTransaction();
        session.persist(sklad);
        session.persist(bramboracka);
        session.persist(obed);
        session.getTransaction().commit();

        System.out.println("Konec.");
        System.exit(0);
    }
}
