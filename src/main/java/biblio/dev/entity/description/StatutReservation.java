package biblio.dev.entity.description;

import biblio.dev.entity.fonctionnalite.Reservation;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "StatutReservation")
public class StatutReservation {
    @EmbeddedId
    private StatutReservationId id;

    @ManyToOne
    @MapsId("idReservation")
    @JoinColumn(name = "idReservation")
    private Reservation reservation;

    @ManyToOne
    @MapsId("idStatut")
    @JoinColumn(name = "idStatut")
    private Statut statut;

    @Temporal(TemporalType.DATE)
    private Date dateStatut;

    // Getters et setters
    public StatutReservationId getId() { return id; }
    public void setId(StatutReservationId id) { this.id = id; }
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }
    public Date getDateStatut() { return dateStatut; }
    public void setDateStatut(Date dateStatut) { this.dateStatut = dateStatut; }
}
