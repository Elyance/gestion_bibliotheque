package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;
import java.util.Date;

import biblio.dev.entity.personne.Adherant;

@Entity
@Table(name = "Demande_prolongement")
public class DemandeProlongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemande;

    @ManyToOne
    @JoinColumn(name = "id_pret")
    private Pret pret;

    @Column(name = "nb_jour_demande")
    private int nbJourDemande;

    @ManyToOne
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_demande")
    private Date dateDemande;

    // Getters et setters
    public int getIdDemande() { return idDemande; }
    public void setIdDemande(int idDemande) { this.idDemande = idDemande; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public int getNbJourDemande() { return nbJourDemande; }
    public void setNbJourDemande(int nbJourDemande) { this.nbJourDemande = nbJourDemande; }

    public Date getDateDemande() { return dateDemande; }
    public void setDateDemande(Date dateDemande) { this.dateDemande = dateDemande; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant;}
}
