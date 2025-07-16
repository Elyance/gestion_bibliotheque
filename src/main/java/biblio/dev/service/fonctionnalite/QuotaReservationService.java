package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.QuotaReservation;
import biblio.dev.repository.fonctionnalite.QuotaReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotaReservationService {
    @Autowired
    private QuotaReservationRepository quotaReservationRepository;

    public int getQuotaMax() {
        // On suppose qu'il n'y a qu'une seule ligne de configuration
        return quotaReservationRepository.findAll().stream()
            .findFirst()
            .map(QuotaReservation::getNbReservation)
            .orElse(3); // valeur par défaut
    }
}

