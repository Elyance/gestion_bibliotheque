package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;

@Entity
@Table(name = "QuotaReservation")
public class QuotaReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int nbReservation;

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNbReservation() { return nbReservation; }
    public void setNbReservation(int nbReservation) { this.nbReservation = nbReservation; }
}