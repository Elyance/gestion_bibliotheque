package biblio.dev.entity.livre;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLivre;

    private String titre;
    private String ISBN;
    private String Edition;
    private String Auteur;
    private int ageLimite;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Livre_Categorie",
               joinColumns = @JoinColumn(name = "idLivre"),
               inverseJoinColumns = @JoinColumn(name = "idCategorie"))
    private List<Categorie> categories = new ArrayList<>();

    // Getters et setters
    public int getIdLivre() { return idLivre; }
    public void setIdLivre(int idLivre) { this.idLivre = idLivre; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public String getEdition() { return Edition; }
    public void setEdition(String Edition) { this.Edition = Edition; }
    public String getAuteur() { return Auteur; }
    public void setAuteur(String Auteur) { this.Auteur = Auteur; }
    public int getAgeLimite() { return ageLimite; }
    public void setAgeLimite(int ageLimite) { this.ageLimite = ageLimite; }
}
