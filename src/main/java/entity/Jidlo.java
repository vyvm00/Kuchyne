package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jidlo")
public class Jidlo {

    @Id
    @Column(name="id_jidlo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    //@Temporal(TemporalType.DATE)
    private Date datum ;

    @Column(name = "pocet_porci")
    private int pocetPorci;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_recept")
    private Recept recept;

    public Jidlo() {
    }

    public Jidlo(Date datum, int pocetPorci, Recept recept) {
        this.datum = datum;
        this.pocetPorci = pocetPorci;
        this.recept = recept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getPocetPorci() {
        return pocetPorci;
    }

    public void setPocetPorci(int pocetPorci) {
        this.pocetPorci = pocetPorci;
    }

    public Recept getRecept() {
        return recept;
    }

    public void setRecept(Recept recept) {
        this.recept = recept;
    }
}
