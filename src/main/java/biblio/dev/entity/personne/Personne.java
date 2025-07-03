package biblio.dev.entity.personne;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonne;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String adresse;

    @Column(nullable = false)
    private String mail;

    private String password;

    // Getters et setters
    public int getIdPersonne() { return idPersonne; }
    public void setIdPersonne(int idPersonne) { this.idPersonne = idPersonne; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
