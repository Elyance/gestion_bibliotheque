package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Admin;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;

    @Temporal(TemporalType.DATE)
    private Date date_;

    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idAdherant")
    private Adherant adherant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idExemplaire")
    private Exemplaire exemplaire;

    // Getters et setters
    public int getIdReservation() { return idReservation; }
    public void setIdReservation(int idReservation) { this.idReservation = idReservation; }
    public Date getDate_() { return date_; }
    public void setDate_(Date date_) { this.date_ = date_; }
    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
}
