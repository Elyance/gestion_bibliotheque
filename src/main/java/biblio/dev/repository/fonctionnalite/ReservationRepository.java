package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import biblio.dev.entity.personne.Adherant;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByAdminIsNull();
    List<Reservation> findByAdminIsNotNull();
    List<Reservation> findByAdherant(Adherant adherant);
}
