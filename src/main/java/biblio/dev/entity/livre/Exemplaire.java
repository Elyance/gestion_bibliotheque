package biblio.dev.entity.livre;

import jakarta.persistence.*;

@Entity
@Table(name = "Exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExemplaire;

    private String numero;

    @ManyToOne
    @JoinColumn(name = "idLivre", nullable = false)
    private Livre livre;

    // Getters et setters
    public int getIdExemplaire() { return idExemplaire; }
    public void setIdExemplaire(int idExemplaire) { this.idExemplaire = idExemplaire; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
}
