package biblio.dev.service.description;

import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import biblio.dev.repository.description.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatutReservationService {
    @Autowired
    private StatutReservationRepository statutReservationRepository;

    public StatutReservation save(StatutReservation statutReservation) {
        return statutReservationRepository.save(statutReservation);
    }
}
