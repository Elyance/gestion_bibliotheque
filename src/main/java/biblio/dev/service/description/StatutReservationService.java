package biblio.dev.service.description;

import biblio.dev.entity.description.StatutReservation;
import biblio.dev.repository.description.StatutReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutReservationService {
    @Autowired
    private StatutReservationRepository statutReservationRepository;

    public StatutReservation save(StatutReservation statutReservation) {
        return statutReservationRepository.save(statutReservation);
    }
    
    public List<StatutReservation> findByReservationId(int idReservation) {
        return statutReservationRepository.findByReservationIdReservationOrderByDateStatutDesc(idReservation);
    }
    
    public StatutReservation findLatestByReservation(int idReservation) {
        List<StatutReservation> statuts = findByReservationId(idReservation);
        return statuts.isEmpty() ? null : statuts.get(0);
    }
    
    public List<StatutReservation> findAll() {
        return statutReservationRepository.findAll();
    }
}