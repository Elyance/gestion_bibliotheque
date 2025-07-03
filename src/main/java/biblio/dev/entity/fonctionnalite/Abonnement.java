package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.Adherant;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Abonnement")
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAbonnement;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @ManyToOne
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    // Getters et setters
    public int getIdAbonnement() { return idAbonnement; }
    public void setIdAbonnement(int idAbonnement) { this.idAbonnement = idAbonnement; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }
}
