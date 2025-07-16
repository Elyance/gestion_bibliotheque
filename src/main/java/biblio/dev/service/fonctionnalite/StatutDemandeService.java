package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.StatutDemande;
import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.repository.fonctionnalite.StatutDemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatutDemandeService {
    @Autowired
    private StatutDemandeRepository repository;

    public StatutDemande save(StatutDemande statutDemande) {
        return repository.save(statutDemande);
    }

    public StatutDemande getStatutActuel(DemandeProlongement demande) {
        return repository.findTopByDemandeProlongementOrderByDateStatutDesc(demande);
    }

    public List<StatutDemande> findByDemande(DemandeProlongement demande) {
        return repository.findByDemandeProlongement(demande);
    }

    public List<StatutDemande> findByStatut(int idStatut) {
        return repository.findByStatut_IdStatut(idStatut);
    }
}