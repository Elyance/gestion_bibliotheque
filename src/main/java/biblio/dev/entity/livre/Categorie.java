package biblio.dev.entity.livre;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;

    private String nomCategorie;

    @ManyToMany
    @JoinTable(name = "Livre_Categorie",
               joinColumns = @JoinColumn(name = "idCategorie"),
               inverseJoinColumns = @JoinColumn(name = "idLivre"))
    private List<Livre> livres;
    

    // Getters et setters
    public int getIdCategorie() { return idCategorie; }
    public void setIdCategorie(int idCategorie) { this.idCategorie = idCategorie; }
    public String getNomCategorie() { return nomCategorie; }
    public void setNomCategorie(String nomCategorie) { this.nomCategorie = nomCategorie; }
}
