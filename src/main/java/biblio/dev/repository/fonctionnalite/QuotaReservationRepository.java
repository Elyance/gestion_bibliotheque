package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.QuotaReservation;
import biblio.dev.entity.personne.TypeAdherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaReservationRepository extends JpaRepository<QuotaReservation, Integer> {
    QuotaReservation findByTypeAdherant(TypeAdherant typeAdherant);
}