package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "surovina_v_receptu")
public class SurovinaVReceptu {


    @EmbeddedId
    private SurovinaVReceptuId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_surovina")   // navazujeme na cizi klic "id surovina" v tabulce surovina_na_sklade
    @MapsId("idSurovina")               // odkazuje na atribut "idSurovina" slozeneho primarniho klice
    private Surovina surovina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recept")
    @MapsId("idRecept")
    private Recept recept;

    @Column
    private int mnozstvi;


    public SurovinaVReceptu() {
    }

    public SurovinaVReceptu(Surovina surovina, Recept recept) {
        this.surovina = surovina;
        this.recept = recept;
        this.id = new SurovinaVReceptuId(surovina.getId(), recept.getId());
    }

    public SurovinaVReceptuId getId() {
        return id;
    }

    public void setId(SurovinaVReceptuId id) {
        this.id = id;
    }

    public Surovina getSurovina() {
        return surovina;
    }

    public void setSurovina(Surovina surovina) {
        this.surovina = surovina;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    /**
     * Vnitrni staticka trida reprezentujici slozeny primarni klic asociacni tabulky realizujici vazbu m:n
     * mezi tabulkami surovina a sklad.
     *
     * Inspirovano: https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
     */
    @Embeddable
    public static class SurovinaVReceptuId implements Serializable {

        @Column(name = "id_surovina")
        private int idSurovina;

        @Column(name = "id_recept")
        private int idRecept;

        private SurovinaVReceptuId() {}

        public SurovinaVReceptuId(int idSurovina, int idRecept) {
            this.idSurovina = idSurovina;
            this.idRecept = idRecept;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            SurovinaVReceptu.SurovinaVReceptuId that = (SurovinaVReceptu.SurovinaVReceptuId) o;
            return Objects.equals(idSurovina, that.idSurovina) && Objects.equals(idRecept, that.idRecept);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idSurovina, idRecept);
        }

        public int getIdSurovina() {
            return idSurovina;
        }

        public void setIdSurovina(int idSurovina) {
            this.idSurovina = idSurovina;
        }

        public int getIdRecept() {
            return idRecept;
        }

        public void setIdRecept(int idRecept) {
            this.idRecept = idRecept;
        }
    }

}
