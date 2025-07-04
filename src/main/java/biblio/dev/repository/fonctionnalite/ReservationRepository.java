package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Ajoutez ici des méthodes personnalisées si besoin
}
