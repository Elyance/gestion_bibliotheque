package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Retour")
public class Retour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRetour;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetour;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    // Getters et setters
    public int getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(int idRetour) {
        this.idRetour = idRetour;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
