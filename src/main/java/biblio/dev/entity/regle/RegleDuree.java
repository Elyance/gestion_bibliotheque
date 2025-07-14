package biblio.dev.entity.regle;

import biblio.dev.entity.personne.TypeAdherant;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RegleDuree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegleDuree;

    private double duree;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "idTypeAdherant")
    private TypeAdherant typeAdherant;

    // Getters et setters
    public int getIdRegleDuree() {
        return idRegleDuree;
    }

    public void setIdRegleDuree(int idRegleDuree) {
        this.idRegleDuree = idRegleDuree;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TypeAdherant getTypeAdherant() {
        return typeAdherant;
    }

    public void setTypeAdherant(TypeAdherant typeAdherant) {
        this.typeAdherant = typeAdherant;
    }
}
