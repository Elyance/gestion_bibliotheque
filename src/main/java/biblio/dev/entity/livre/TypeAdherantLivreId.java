package biblio.dev.entity.livre;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeAdherantLivreId implements Serializable {
    private Integer idTypeAdherant;
    private Integer idLivre;

    // Getters, setters, equals, hashCode
    public Integer getIdTypeAdherant() { return idTypeAdherant; }
    public void setIdTypeAdherant(Integer idTypeAdherant) { this.idTypeAdherant = idTypeAdherant; }
    public Integer getIdLivre() { return idLivre; }
    public void setIdLivre(Integer idLivre) { this.idLivre = idLivre; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeAdherantLivreId)) return false;
        TypeAdherantLivreId that = (TypeAdherantLivreId) o;
        return Objects.equals(idTypeAdherant, that.idTypeAdherant) &&
               Objects.equals(idLivre, that.idLivre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idTypeAdherant, idLivre);
    }
}
