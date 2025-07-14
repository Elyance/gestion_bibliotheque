package biblio.dev.entity.regle;

import java.util.Date;
import jakarta.persistence.*;

import biblio.dev.entity.personne.TypeAdherant;

@Entity
@Table(name = "RegleNbLivre")
public class RegleNbLivre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegleNbLivre;

    private int nbLivre;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idTypeAdherant", nullable = false)
    private TypeAdherant typeAdherant;

    // Getters et setters
    public int getIdRegleNbLivre() { return idRegleNbLivre; }
    public void setIdRegleNbLivre(int idRegleNbLivre) { this.idRegleNbLivre = idRegleNbLivre; }

    public int getNbLivre() { return nbLivre; }
    public void setNbLivre(int nbLivre) { this.nbLivre = nbLivre; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }
}
