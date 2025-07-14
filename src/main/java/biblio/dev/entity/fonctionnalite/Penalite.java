package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Penalite")
public class Penalite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPenalite;

    @Column(nullable = false)
    private int nbJourPenalite;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "idPret", nullable = false, unique = true)
    private Pret pret;

    // === Getters & Setters ===

    public int getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(int idPenalite) {
        this.idPenalite = idPenalite;
    }

    public int getNbJourPenalite() {
        return nbJourPenalite;
    }

    public void setNbJourPenalite(int nbJourPenalite) {
        this.nbJourPenalite = nbJourPenalite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
