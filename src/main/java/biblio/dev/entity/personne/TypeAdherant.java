package biblio.dev.entity.personne;

import jakarta.persistence.*;

import biblio.dev.entity.livre.Livre;

import java.util.*;

@Entity
@Table(name = "TypeAdherant")
public class TypeAdherant {
    @Id
    private Integer idTypeAdherant;

    @Column(nullable = false)
    private String nomTypeAdherant;

    @ManyToMany
    @JoinTable(name = "TypeAdherant_Livre",
               joinColumns = @JoinColumn(name = "idLivre"),
               inverseJoinColumns = @JoinColumn(name = "idTypeAdherant"))
    List<Livre> livres= new ArrayList<>();

    // Getters et setters
    public Integer getIdTypeAdherant() { return idTypeAdherant; }
    public void setIdTypeAdherant(Integer idTypeAdherant) { this.idTypeAdherant = idTypeAdherant; }
    public String getNomTypeAdherant() { return nomTypeAdherant; }
    public void setNomTypeAdherant(String nomTypeAdherant) { this.nomTypeAdherant = nomTypeAdherant; }
}
