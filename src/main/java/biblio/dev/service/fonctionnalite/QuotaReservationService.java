package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.QuotaReservation;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.fonctionnalite.QuotaReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotaReservationService {
    @Autowired
    private QuotaReservationRepository quotaReservationRepository;

    public int getQuotaMax(TypeAdherant typeAdherant) {
        QuotaReservation quota = quotaReservationRepository.findByTypeAdherant(typeAdherant);
        return quota != null ? quota.getNbReservation() : 3; // valeur par défaut
    }
}

