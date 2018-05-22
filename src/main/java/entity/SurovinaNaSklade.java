package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "surovina_na_sklade")
public class SurovinaNaSklade {

    @EmbeddedId
    private SurovinaNaSkladeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_surovina")   // navazujeme na cizi klic "id surovina" v tabulce surovina_na_sklade
    @MapsId("idSurovina")               // odkazuje na atribut "idSurovina" slozeneho primarniho klice
    private Surovina surovina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sklad")
    @MapsId("idSklad")
    private Sklad sklad;

    @Column
    private int mnozstvi;

    @Column(name = "minimalni_mnozstvi")
    private int minimalniMnozstvi;


    public SurovinaNaSklade() {
    }

    public SurovinaNaSklade(Surovina surovina, Sklad sklad) {
        this.surovina = surovina;
        this.sklad = sklad;
        this.id = new SurovinaNaSkladeId(surovina.getId(), sklad.getId());
    }

    public SurovinaNaSkladeId getId() {
        return id;
    }

    public void setId(SurovinaNaSkladeId id) {
        this.id = id;
    }

    public Surovina getSurovina() {
        return surovina;
    }

    public void setSurovina(Surovina surovina) {
        this.surovina = surovina;
    }

    public Sklad getSklad() {
        return sklad;
    }

    public void setSklad(Sklad sklad) {
        this.sklad = sklad;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public int getMinimalniMnozstvi() { return minimalniMnozstvi; }

    public void setMinimalniMnozstvi(int minimalniMnozstvi) { this.minimalniMnozstvi = minimalniMnozstvi; }

    /**
     * Vnitrni staticka trida reprezentujici slozeny primarni klic asociacni tabulky realizujici vazbu mn:n
     * mezi tabulkami surovina a sklad.
     *
     * Inspirovano: https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
     */
    @Embeddable
    public static class SurovinaNaSkladeId implements Serializable {

        @Column(name = "id_surovina")
        private int idSurovina;

        @Column(name = "id_sklad")
        private int idSklad;

        private SurovinaNaSkladeId() {}

        public SurovinaNaSkladeId(int idSurovina, int idSklad) {
            this.idSurovina = idSurovina;
            this.idSklad = idSklad;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            SurovinaNaSkladeId that = (SurovinaNaSkladeId) o;
            return Objects.equals(idSurovina, that.idSurovina) && Objects.equals(idSklad, that.idSklad);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idSurovina, idSklad);
        }

    }

}
