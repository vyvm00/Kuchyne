package entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "recept")
public class Recept {

    @Id
    @Column(name="id_recept")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nazev;

    @Column
    private String popis;

    @Column(name = "cas_pripravy")
    private int casPripravy;

    @Column(name = "cena_porce")
    private int cenaPorce;

    @OneToMany(
            mappedBy = "recept", // mapovani je nastaveno na atributu "recetp" ve tride entity.SurovinaVReceptu
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SurovinaVReceptu> suroviny;


    /**
     * Pridani suroviny na recept.
     * Interne vytvori instanci asociacni tridy mezi receptem a surovinou
     * @param surovina
     * @param mnozstvi
     */
    public void addSurovina(Surovina surovina, int mnozstvi) {
        SurovinaVReceptu svr = new SurovinaVReceptu(surovina, this);
        svr.setMnozstvi(mnozstvi);
        suroviny.add(svr);
    }

    /**
     * Odstrani surovinu z receptu.
     * Interne odstrani instanci asociacni tabulky mezi receptem a surovinou
     * a teto instanci zaroven odstrani vazby na recept i produkt
     * @param surovina
     */
    public void removeSurovina(Surovina surovina) {
        for (Iterator<SurovinaVReceptu> iterator = suroviny.iterator(); iterator.hasNext(); ) {
            SurovinaVReceptu svr = iterator.next();

            if (svr.getRecept().equals(this) && svr.getSurovina().equals(surovina)) {
                iterator.remove();
                svr.getRecept().getSuroviny().remove(svr);
                svr.setSurovina(null);
                svr.setRecept(null);
            }
        }
    }

    @Override
    public String toString() {
        return nazev;
    }

    public Recept() {
        suroviny = new ArrayList<>();
    }

    public Recept(String nazev, String popis, int casPripravy, int cenaPorce) {
        this.nazev = nazev;
        this.popis = popis;
        this.casPripravy = casPripravy;
        this.cenaPorce = cenaPorce;
        suroviny = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getCasPripravy() {
        return casPripravy;
    }

    public void setCasPripravy(int casPripravy) {
        this.casPripravy = casPripravy;
    }

    public List<SurovinaVReceptu> getSuroviny() { return suroviny; }

    public void setSuroviny(List<SurovinaVReceptu> suroviny) {
        this.suroviny = suroviny;
    }

    public int getCenaPorce() { return cenaPorce; }

    public void setCenaPorce(int cenaPorce) {
        this.cenaPorce = cenaPorce;
    }
}