package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.repository.fonctionnalite.DemandeProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biblio.dev.entity.fonctionnalite.Pret;

import java.util.List;
import biblio.dev.entity.personne.Adherant;

@Service
public class DemandeProlongementService {
    @Autowired
    private DemandeProlongementRepository repository;

    public DemandeProlongement save(DemandeProlongement demande) {
        return repository.save(demande);
    }
    public DemandeProlongement findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<DemandeProlongement> findByPret(Pret pret) {
        // exemple si tu veux retrouver les demandes pour un prêt
        return repository.findAll().stream()
            .filter(d -> d.getPret().getIdPret() == pret.getIdPret())
            .toList();
    }
    public List<DemandeProlongement> findAll() {
        return repository.findAll();
    }

    public List<DemandeProlongement> findByAdherant(Adherant adherant) {
        // exemple si tu veux retrouver les demandes pour un adhérent
        return repository.findByAdherant(adherant);
    }
}