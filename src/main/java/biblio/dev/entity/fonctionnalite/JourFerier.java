package biblio.dev.entity.fonctionnalite;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "JourFerier")
public class JourFerier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private Date date;

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}