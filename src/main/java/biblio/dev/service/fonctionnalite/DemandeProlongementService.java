package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.entity.fonctionnalite.StatutDemande;
import biblio.dev.repository.fonctionnalite.DemandeProlongementRepository;
import biblio.dev.service.fonctionnalite.StatutDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.personne.Adherant;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandeProlongementService {
    
    @Autowired
    private DemandeProlongementRepository demandeProlongementRepository;
    
    @Autowired
    private StatutDemandeService statutDemandeService;
    
    public DemandeProlongement save(DemandeProlongement demande) {
        return demandeProlongementRepository.save(demande);
    }
    public DemandeProlongement findById(Integer id) {
        return demandeProlongementRepository.findById(id).orElse(null);
    }

    public List<DemandeProlongement> findByPret(Pret pret) {
        // exemple si tu veux retrouver les demandes pour un prêt
        return demandeProlongementRepository.findAll().stream()
            .filter(d -> d.getPret().getIdPret() == pret.getIdPret())
            .toList();
    }
    public List<DemandeProlongement> findAll() {
        return demandeProlongementRepository.findAll();
    }

    public List<DemandeProlongement> findByAdherant(Adherant adherant) {
        // exemple si tu veux retrouver les demandes pour un adhérent
        return demandeProlongementRepository.findByAdherant(adherant);
    }
    
    /**
     * Récupère les demandes en attente (idStatut = 1)
     */
    public List<DemandeProlongement> getDemandesEnAttente() {
        List<DemandeProlongement> toutes = demandeProlongementRepository.findAll();
        System.out.println("DEBUG getDemandesEnAttente - Total demandes: " + toutes.size());
        
        return toutes.stream()
            .filter(demande -> {
                StatutDemande statutActuel = statutDemandeService.getStatutActuel(demande);
                boolean estEnAttente = statutActuel != null && statutActuel.getStatut().getIdStatut() == 1;
                System.out.println("Demande " + demande.getIdDemande() + " -> En attente: " + estEnAttente);
                return estEnAttente;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Récupère les demandes validées (idStatut = 2)
     */
    public List<DemandeProlongement> getDemandesValidees() {
        List<DemandeProlongement> toutes = demandeProlongementRepository.findAll();
        System.out.println("DEBUG getDemandesValidees - Total demandes: " + toutes.size());
        
        return toutes.stream()
            .filter(demande -> {
                StatutDemande statutActuel = statutDemandeService.getStatutActuel(demande);
                boolean estValidee = statutActuel != null && statutActuel.getStatut().getIdStatut() == 2;
                System.out.println("Demande " + demande.getIdDemande() + " -> Validée: " + estValidee);
                return estValidee;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Récupère les demandes refusées (idStatut = 3)
     */
    public List<DemandeProlongement> getDemandesRefusees() {
        List<DemandeProlongement> toutes = demandeProlongementRepository.findAll();
        System.out.println("DEBUG getDemandesRefusees - Total demandes: " + toutes.size());
        
        return toutes.stream()
            .filter(demande -> {
                StatutDemande statutActuel = statutDemandeService.getStatutActuel(demande);
                boolean estRefusee = statutActuel != null && statutActuel.getStatut().getIdStatut() == 3;
                System.out.println("Demande " + demande.getIdDemande() + " -> Refusée: " + estRefusee);
                return estRefusee;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Récupère les demandes par statut
     */
    public List<DemandeProlongement> getDemandesParStatut(int idStatut) {
        return demandeProlongementRepository.findAll().stream()
            .filter(demande -> {
                StatutDemande statutActuel = statutDemandeService.getStatutActuel(demande);
                return statutActuel != null && statutActuel.getStatut().getIdStatut() == idStatut;
            })
            .collect(Collectors.toList());
    }
}