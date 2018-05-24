package entity;

import javax.persistence.*;

@Entity
@Table(name = "surovina")
public class Surovina {

    @Id
    @Column(name="id_surovina")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nazev;

    @Column(name = "merna_jednotka")
    private String mernaJednotka;


    public Surovina() {
    }

    public Surovina(String nazev, String mernaJednotka) {
        this.nazev = nazev;
        this.mernaJednotka = mernaJednotka;
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

    public String getMernaJednotka() {
        return mernaJednotka;
    }

    public void setMernaJednotka(String mernaJednotka) {
        this.mernaJednotka = mernaJednotka;
    }


}
