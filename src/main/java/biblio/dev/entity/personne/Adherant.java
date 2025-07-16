package biblio.dev.entity.personne;

import jakarta.persistence.*;

@Entity
@Table(name = "Adherant")
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdherant;

    @OneToOne
    @JoinColumn(name = "idPersonne", referencedColumnName = "idPersonne", nullable = false, unique = true)
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "idTypeAdherant", referencedColumnName = "idTypeAdherant", nullable = false)
    private TypeAdherant typeAdherant;

    @Column(name = "numero_adherant", unique = true, nullable = false)
    private String numeroAdherant;

    // Getters et setters
    public int getIdAdherant() { return idAdherant; }
    public void setIdAdherant(int idAdherant) { this.idAdherant = idAdherant; }
    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }
    public TypeAdherant getTypeAdherant() { return typeAdherant; }
    public void setTypeAdherant(TypeAdherant typeAdherant) { this.typeAdherant = typeAdherant; }
    public String getNumeroAdherant() { return numeroAdherant; }
    public void setNumeroAdherant(String numeroAdherant) { this.numeroAdherant = numeroAdherant; }
}
