package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.TypeAdherant;
import jakarta.persistence.*;

@Entity
@Table(name = "PenaliteQuota")
public class PenaliteQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int nbJourPenalite;

    @ManyToOne
    @JoinColumn(name = "idTypeAdherant", nullable = false)
    private TypeAdherant typeAdherant;

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getNbJourPenalite() { return nbJourPenalite; }
    public void setNbJourPenalite(int nbJourPenalite) { this.nbJourPenalite = nbJourPenalite; }
    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }
}