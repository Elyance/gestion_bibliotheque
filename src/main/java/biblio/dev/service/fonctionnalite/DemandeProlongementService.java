package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.repository.fonctionnalite.DemandeProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemandeProlongementService {

    @Autowired
    private DemandeProlongementRepository demandeProlongementRepository;

    public List<DemandeProlongement> findAll() {
        return demandeProlongementRepository.findAll();
    }

    public Optional<DemandeProlongement> findById(Integer id) {
        return demandeProlongementRepository.findById(id);
    }

    public DemandeProlongement save(DemandeProlongement demandeProlongement) {
        return demandeProlongementRepository.save(demandeProlongement);
    }

    public void deleteById(Integer id) {
        demandeProlongementRepository.deleteById(id);
    }

    public List<DemandeProlongement> findByPret(Pret pret) {
        return demandeProlongementRepository.findByPret(pret);
    }

    public List<DemandeProlongement> findDemandesEnAttente() {
        return demandeProlongementRepository.findAll(); // Vous pouvez ajouter un statut si nécessaire
    }
}