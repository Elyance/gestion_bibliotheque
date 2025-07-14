package biblio.dev.entity.personne;

import jakarta.persistence.*;

import biblio.dev.entity.livre.*;

import java.util.*;

@Entity
@Table(name = "TypeAdherant")
public class TypeAdherant {
    @Id
    private Integer idTypeAdherant;

    @Column(nullable = false)
    private String nomTypeAdherant;

    @OneToMany(mappedBy = "typeAdherant")
    private List<TypeAdherantLivre> typeAdherantLivres = new ArrayList<>();

    // Getters et setters
    public Integer getIdTypeAdherant() { return idTypeAdherant; }
    public void setIdTypeAdherant(Integer idTypeAdherant) { this.idTypeAdherant = idTypeAdherant; }
    public String getNomTypeAdherant() { return nomTypeAdherant; }
    public void setNomTypeAdherant(String nomTypeAdherant) { this.nomTypeAdherant = nomTypeAdherant; }
}
