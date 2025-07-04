package biblio.dev.entity.fonctionnalite;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.livre.Exemplaire;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pret")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPret;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

    @ManyToOne
    @JoinColumn(name = "idTypePret", nullable = false)
    private TypePret typePret;

    @ManyToOne
    @JoinColumn(name = "idAdmin", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    @ManyToOne
    @JoinColumn(name = "idExemplaire", nullable = false)
    private Exemplaire exemplaire;

    // Constructeurs
    public Pret() {}

    public Pret(LocalDateTime dateDebut, LocalDateTime dateFin, TypePret typePret, 
                Admin admin, Adherant adherant, Exemplaire exemplaire) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typePret = typePret;
        this.admin = admin;
        this.adherant = adherant;
        this.exemplaire = exemplaire;
    }

    // Getters et setters
    public int getIdPret() { return idPret; }
    public void setIdPret(int idPret) { this.idPret = idPret; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public TypePret getTypePret() { return typePret; }
    public void setTypePret(TypePret typePret) { this.typePret = typePret; }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

    public Adherant getAdherant() { return adherant; }
    public void setAdherant(Adherant adherant) { this.adherant = adherant; }

    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }

    // Méthodes utilitaires
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(dateFin);
    }

    public String getStatutPret() {
        return isExpired() ? "Expiré" : "En cours";
    }
}