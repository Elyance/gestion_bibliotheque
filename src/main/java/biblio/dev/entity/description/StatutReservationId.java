package biblio.dev.entity.description;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatutReservationId implements Serializable {
    private int idReservation;
    private int idStatut;

    public StatutReservationId() {}
    public StatutReservationId(int idReservation, int idStatut) {
        this.idReservation = idReservation;
        this.idStatut = idStatut;
    }
    public int getIdReservation() { return idReservation; }
    public void setIdReservation(int idReservation) { this.idReservation = idReservation; }
    public int getIdStatut() { return idStatut; }
    public void setIdStatut(int idStatut) { this.idStatut = idStatut; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatutReservationId that = (StatutReservationId) o;
        return idReservation == that.idReservation && idStatut == that.idStatut;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idStatut);
    }
}
