package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Prolongement;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.ProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProlongementService {
    @Autowired
    private ProlongementRepository prolongementRepository;

    public void save(Prolongement prolongement) {
        prolongementRepository.save(prolongement);
    }

    public List<Prolongement> getProlongementsByAdherant(Adherant adherant) {
        return prolongementRepository.findByAdherant(adherant);
    }
}