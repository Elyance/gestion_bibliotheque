package biblio.dev.entity.personne;

import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdmin;

    @ManyToOne
    @JoinColumn(name = "idPersonne", referencedColumnName = "idPersonne", nullable = false)
    private Personne personne;

    // Getters et setters
    public int getIdAdmin() { return idAdmin; }
    public void setIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }
    public Personne getPersonne() { return personne; }
    public void setPersonne(Personne personne) { this.personne = personne; }
}
