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


    @Override
    public String toString() {
        return nazev;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Surovina surovina = (Surovina) o;

        if (id != surovina.id) return false;
        if (nazev != null ? !nazev.equals(surovina.nazev) : surovina.nazev != null) return false;
        return mernaJednotka != null ? mernaJednotka.equals(surovina.mernaJednotka) : surovina.mernaJednotka == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nazev != null ? nazev.hashCode() : 0);
        result = 31 * result + (mernaJednotka != null ? mernaJednotka.hashCode() : 0);
        return result;
    }
}
