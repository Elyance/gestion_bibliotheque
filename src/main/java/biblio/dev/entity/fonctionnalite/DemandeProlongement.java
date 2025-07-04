package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DemandeProlongement")
public class DemandeProlongement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemande;

    @Column(nullable = false)
    private LocalDate dateDemande;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    // Constructeurs
    public DemandeProlongement() {}

    public DemandeProlongement(LocalDate dateDemande, Pret pret) {
        this.dateDemande = dateDemande;
        this.pret = pret;
    }

    // Getters et setters
    public int getIdDemande() { return idDemande; }
    public void setIdDemande(int idDemande) { this.idDemande = idDemande; }

    public LocalDate getDateDemande() { return dateDemande; }
    public void setDateDemande(LocalDate dateDemande) { this.dateDemande = dateDemande; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }
}