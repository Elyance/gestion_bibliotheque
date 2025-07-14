package biblio.dev.entity.description;

import biblio.dev.entity.fonctionnalite.Reservation;
import jakarta.persistence.*;

@Entity
@Table(name = "Statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatut;

    @Column(nullable = false)
    private String nomStatut;

    // Pour la table StatutReservation (relation N-N avec Reservation, avec date)
    @ManyToMany
    @JoinTable(
        name = "StatutReservation",
        joinColumns = @JoinColumn(name = "idStatut"),
        inverseJoinColumns = @JoinColumn(name = "idReservation")
    )
    private java.util.List<Reservation> reservations;

    // Getters et setters
    public int getIdStatut() { return idStatut; }
    public void setIdStatut(int idStatut) { this.idStatut = idStatut; }
    public String getNomStatut() { return nomStatut; }
    public void setNomStatut(String nomStatut) { this.nomStatut = nomStatut; }
    public java.util.List<Reservation> getReservations() { return reservations; }
    public void setReservations(java.util.List<Reservation> reservations) { this.reservations = reservations; }
}
