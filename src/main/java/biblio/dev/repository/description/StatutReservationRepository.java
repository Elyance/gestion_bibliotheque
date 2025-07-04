package biblio.dev.repository.description;

import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutReservationRepository extends JpaRepository<StatutReservation, StatutReservationId> {
}
