package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;
import java.util.Date;
import biblio.dev.entity.description.Statut;

@Entity
@Table(name = "statut_demande")
public class StatutDemande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatutDemande;

    @ManyToOne
    @JoinColumn(name = "idDemande")
    private DemandeProlongement demandeProlongement;

    @ManyToOne
    @JoinColumn(name = "idStatut")
    private Statut statut; // Utilise la même table que pour les réservations

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_statut")
    private Date dateStatut;

    // Getters & setters
    public int getIdStatutDemande() { return idStatutDemande; }
    public void setIdStatutDemande(int idStatutDemande) { this.idStatutDemande = idStatutDemande; }

    public DemandeProlongement getDemandeProlongement() { return demandeProlongement; }
    public void setDemandeProlongement(DemandeProlongement demandeProlongement) { this.demandeProlongement = demandeProlongement; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public Date getDateStatut() { return dateStatut; }
    public void setDateStatut(Date dateStatut) { this.dateStatut = dateStatut; }

    
}