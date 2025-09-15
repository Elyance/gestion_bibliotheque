package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.Adherant;
import jakarta.persistence.*;
import biblio.dev.entity.fonctionnalite.Pret;

@Entity
@Table(name = "Prolongement")
public class Prolongement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    // Getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }
    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }
}