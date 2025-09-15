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
        System.out.println("SAUVEGARDE StatutDemande - Demande: " + statutDemande.getDemandeProlongement().getIdDemande() + 
                          ", Statut: " + statutDemande.getStatut().getIdStatut() + 
                          ", Date: " + statutDemande.getDateStatut() +
                          ", ID: " + statutDemande.getIdStatutDemande());
        return repository.save(statutDemande);
    }

    public StatutDemande getStatutActuel(DemandeProlongement demande) {
        // Tri par date DESC puis par ID DESC - le plus récent en premier
        List<StatutDemande> statuts = repository.findByDemandeWithStatutOrderByDateDesc(demande);
        
        System.out.println("DEBUG getStatutActuel - Demande " + demande.getIdDemande() + " a " + statuts.size() + " statuts");
        
        if (statuts.isEmpty()) {
            System.out.println("  -> Aucun statut trouvé");
            return null;
        }
        
        // Afficher tous les statuts pour debug
        for (int i = 0; i < statuts.size(); i++) {
            StatutDemande s = statuts.get(i);
            System.out.println("  Statut " + (i+1) + " - ID: " + s.getIdStatutDemande() + 
                             ", Date: " + s.getDateStatut() + 
                             ", Statut: " + s.getStatut().getNomStatut());
        }
        
        // Le premier est le plus récent (ORDER BY date DESC, id DESC)
        StatutDemande statutActuel = statuts.get(0);
        System.out.println("  -> Statut actuel retenu: ID " + statutActuel.getIdStatutDemande() + 
                         " - " + statutActuel.getStatut().getNomStatut() + 
                         " (ID statut: " + statutActuel.getStatut().getIdStatut() + ")" +
                         " - Date: " + statutActuel.getDateStatut());
        
        return statutActuel;
    }

    public List<StatutDemande> findByDemande(DemandeProlongement demande) {
        return repository.findByDemandeProlongement(demande);
    }

    public List<StatutDemande> findByStatut(int idStatut) {
        return repository.findByStatut_IdStatut(idStatut);
    }
}