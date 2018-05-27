package entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "sklad")
public class Sklad {

    @Id
    @Column(name="id_sklad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nazev;

    @Column
    private String adresa;

    @Column
    private int plocha;

    @OneToMany(
            mappedBy = "sklad",         // mapovani je nastaveno na atributu "sklad" ve tride entity.SurovinaNaSklade
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SurovinaNaSklade> suroviny;


    /**
     * Pridani suroviny na sklad.
     * Interne vytvori instanci asociacni tridy mezi skladem a surovinou
     * @param surovina
     * @param mnozstvi
     */
    public void addSurovina(Surovina surovina, int mnozstvi) {
        SurovinaNaSklade sns = new SurovinaNaSklade(surovina, this);
        sns.setMnozstvi(mnozstvi);
        suroviny.add(sns);
    }

    /**
     * Odstrani surovinu ze skladu.
     * Interne odstrani instanci asociacni tabulky mezi skladem a surovinou
     * a teto instanci zaroven odstrani vazby na sklad i produkt
     * @param surovina
     */
    public void removeSurovina(Surovina surovina) {
        for (Iterator<SurovinaNaSklade> iterator = suroviny.iterator(); iterator.hasNext(); ) {
            SurovinaNaSklade sns = iterator.next();

            if (sns.getSklad().equals(this) && sns.getSurovina().equals(surovina)) {
                iterator.remove();
                sns.setSurovina(null);
                sns.setSklad(null);
            }
        }
    }


    public Sklad() {
        suroviny = new ArrayList<>();
    }

    public Sklad(String nazev, String adresa, int plocha) {
        this.nazev = nazev;
        this.adresa = adresa;
        this.plocha = plocha;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getPlocha() {
        return plocha;
    }

    public void setPlocha(int plocha) {
        this.plocha = plocha;
    }

    public List<SurovinaNaSklade> getSuroviny() {
        return suroviny;
    }

    public void List(List<SurovinaNaSklade> suroviny) {
        this.suroviny = suroviny;
    }
}
