package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.TypeAdherant;
import jakarta.persistence.*;

@Entity
@Table(name = "QuotaProlongement")
public class QuotaProlongement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int nbProlongement;

    @ManyToOne
    @JoinColumn(name = "idTypeAdherant", nullable = false)
    private TypeAdherant typeAdherant;

    // Getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNbProlongement() { return nbProlongement; }
    public void setNbProlongement(int nbProlongement) { this.nbProlongement = nbProlongement; }
    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }
}