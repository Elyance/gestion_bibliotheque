package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;

@Entity
@Table(name = "TypePret")
public class TypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypePret;

    @Column(nullable = false, length = 50)
    private String nomType;

    // Constructeurs
    public TypePret() {}

    public TypePret(String nomType) {
        this.nomType = nomType;
    }

    // Getters et setters
    public int getIdTypePret() { return idTypePret; }
    public void setIdTypePret(int idTypePret) { this.idTypePret = idTypePret; }

    public String getNomType() { return nomType; }
    public void setNomType(String nomType) { this.nomType = nomType; }
}