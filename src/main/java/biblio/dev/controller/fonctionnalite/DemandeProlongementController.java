package biblio.dev.controller.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.entity.fonctionnalite.StatutDemande;
import biblio.dev.entity.description.Statut;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.service.fonctionnalite.PretService;
import biblio.dev.service.fonctionnalite.DemandeProlongementService;
import biblio.dev.service.fonctionnalite.StatutDemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import biblio.dev.service.description.StatutService;

import java.util.Date;
import java.util.*;

import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.fonctionnalite.Retour;
import biblio.dev.service.fonctionnalite.RetourService;
import biblio.dev.entity.fonctionnalite.Prolongement;
import biblio.dev.service.fonctionnalite.ProlongementService;
import biblio.dev.service.fonctionnalite.QuotaProlongementService;
import biblio.dev.service.regle.RegleDureeService;



@Controller
public class DemandeProlongementController {
    
    @Autowired
    private DemandeProlongementService demandeProlongementService;
    @Autowired
    private StatutDemandeService statutDemandeService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private ProlongementService prolongementService;
    @Autowired
    private RetourService retourService;
    @Autowired
    private QuotaProlongementService quotaProlongementService;

    @Autowired
    private PretService pretService;

    @Autowired
    private RegleDureeService regleDureeService;

    @GetMapping("/demande-prolongement")
    public String formDemandeProlongement(@RequestParam("idPret") int idPret, Model model) {
        Pret pret = pretService.findById(idPret).orElse(null);
        model.addAttribute("pret", pret);
        
        // Format datetime-local pour cohérence
        String currentDateTime = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new Date());
        model.addAttribute("currentDateTime", currentDateTime);
        
        return "demande-prolongement";
    }

    @PostMapping("/demande-prolongement")
    public String submitDemandeProlongement(
            @RequestParam("idPret") int idPret,
            @RequestParam("nbJourDemande") int nbJourDemande,
            @RequestParam("dateDemande") String dateDemandeStr,
            @SessionAttribute("adherantConnecte") Adherant adherant,
            Model model) {
        
        System.out.println("=== NOUVELLE DEMANDE PROLONGEMENT ===");
        System.out.println("Date demande reçue: " + dateDemandeStr);
        
        Pret pret = pretService.findById(idPret).orElse(null);
        
        // Vérification du quota de prolongement
        int nbProlongements = prolongementService.getProlongementsByAdherant(adherant).size();
        int quotaMax = quotaProlongementService.getQuotaMax(adherant.getTypeAdherant());
        
        if (nbProlongements >= quotaMax) {
            model.addAttribute("error", "Quota de prolongements atteint (" + quotaMax + ").");
            model.addAttribute("pret", pret);
            return "demande-prolongement";
        }
        
        DemandeProlongement demande = new DemandeProlongement();
        demande.setPret(pret);
        demande.setAdherant(adherant);
        demande.setNbJourDemande(nbJourDemande);
        
        // Si dateDemandeStr contient 'T', c'est un datetime-local
        if (dateDemandeStr.contains("T")) {
            java.sql.Timestamp timestampDemande = java.sql.Timestamp.valueOf(dateDemandeStr.replace("T", " ") + ":00");
            demande.setDateDemande(new java.sql.Date(timestampDemande.getTime()));
        } else {
            demande.setDateDemande(java.sql.Date.valueOf(dateDemandeStr));
        }
        
        demandeProlongementService.save(demande);

        // Ajout du statut "En attente" (idStatut = 1)
        StatutDemande statutDemande = new StatutDemande();
        statutDemande.setDemandeProlongement(demande);
        Statut statutEnAttente = statutService.findById(1).orElse(null);
        statutDemande.setStatut(statutEnAttente);
        
        // Utiliser un timestamp précis
        java.sql.Timestamp timestampDemande;
        if (dateDemandeStr.contains("T")) {
            timestampDemande = java.sql.Timestamp.valueOf(dateDemandeStr.replace("T", " ") + ":00");
        } else {
            timestampDemande = new java.sql.Timestamp(System.currentTimeMillis());
        }
        
        statutDemande.setDateStatut(timestampDemande);
        statutDemandeService.save(statutDemande);
        
        System.out.println("Statut DEMANDE créé à : " + timestampDemande);

        model.addAttribute("message", "Demande de prolongement envoyée !");
        return "redirect:/mes-prets";
    }

    // Liste des demandes de prolongement (admin)
    @GetMapping("/list-demandes-prolongement")
    public String listDemandesProlongementAdmin(Model model) {
        // Utilisation des nouvelles méthodes du service
        List<DemandeProlongement> demandesEnAttente = demandeProlongementService.getDemandesEnAttente();
        List<DemandeProlongement> demandesValidees = demandeProlongementService.getDemandesValidees();
        List<DemandeProlongement> demandesRefusees = demandeProlongementService.getDemandesRefusees();
        
        System.out.println("=== DEBUG LISTES DEMANDES ===");
        System.out.println("Demandes en attente: " + demandesEnAttente.size());
        System.out.println("Demandes validées: " + demandesValidees.size());
        System.out.println("Demandes refusées: " + demandesRefusees.size());
        
        // Debug détaillé
        for (DemandeProlongement demande : demandesEnAttente) {
            System.out.println("  En attente - ID: " + demande.getIdDemande() + 
                              ", Adhérant: " + demande.getAdherant().getPersonne().getNom());
        }
        
        for (DemandeProlongement demande : demandesValidees) {
            System.out.println("  Validée - ID: " + demande.getIdDemande() + 
                              ", Adhérant: " + demande.getAdherant().getPersonne().getNom());
        }
        
        for (DemandeProlongement demande : demandesRefusees) {
            System.out.println("  Refusée - ID: " + demande.getIdDemande() + 
                              ", Adhérant: " + demande.getAdherant().getPersonne().getNom());
        }
        
        model.addAttribute("demandesEnAttente", demandesEnAttente);
        model.addAttribute("demandesValidees", demandesValidees);
        model.addAttribute("demandesRefusees", demandesRefusees);
        return "list-demandes-prolongement";
    }

    // Validation d'une demande de prolongement
    @PostMapping("/valider-demande-prolongement")
    public String validerDemande(
            @RequestParam("idDemande") int idDemande,
            @RequestParam("dateValidation") String dateValidationStr,
            @SessionAttribute("adminConnecte") Admin admin,
            Model model
    ) {
        System.out.println("=== VALIDATION DEMANDE PROLONGEMENT ===");
        System.out.println("ID Demande: " + idDemande);
        System.out.println("Date validation reçue: " + dateValidationStr);
        
        DemandeProlongement demande = demandeProlongementService.findById(idDemande);
        if (demande == null) {
            model.addAttribute("error", "Demande introuvable.");
            return "redirect:/list-demandes-prolongement";
        }
        Pret ancienPret = demande.getPret();
        if (ancienPret == null) {
            model.addAttribute("error", "Aucun prêt associé à cette demande.");
            return "redirect:/list-demandes-prolongement";
        }

        // 1. Retourner l'ancien prêt
        // Convertir datetime-local en Date pour le retour
        java.sql.Timestamp timestampValidation = java.sql.Timestamp.valueOf(dateValidationStr.replace("T", " ") + ":00");
        
        Retour retour = new Retour();
        retour.setPret(ancienPret);
        retour.setDateRetour(timestampValidation);
        retourService.save(retour);
        
        System.out.println("Retour créé à: " + timestampValidation);

        // 2. Créer le nouveau prêt (prolongement)
        Pret nouveauPret = new Pret();
        nouveauPret.setAdherant(ancienPret.getAdherant());
        nouveauPret.setExemplaire(ancienPret.getExemplaire());
        nouveauPret.setTypePret(ancienPret.getTypePret());
        nouveauPret.setAdmin(admin);
        nouveauPret.setDateDebut(timestampValidation);

        // Calcul de la nouvelle date de fin en utilisant la règle de durée
        java.time.LocalDateTime dateTimeValidation = timestampValidation.toLocalDateTime();
        double dureeJour = regleDureeService.getDureePourTypeAdherantAlaDate(
            ancienPret.getAdherant().getTypeAdherant(), 
            dateTimeValidation.toLocalDate()
        );
        
        java.time.LocalDateTime dateTimeFin = dateTimeValidation.plusDays((long) dureeJour);
        java.sql.Timestamp timestampFin = java.sql.Timestamp.valueOf(dateTimeFin);
        
        nouveauPret.setDateFin(timestampFin);
        pretService.save(nouveauPret);
        
        System.out.println("Nouveau prêt créé:");
        System.out.println("  - Début: " + timestampValidation);
        System.out.println("  - Fin: " + timestampFin);
        System.out.println("  - Durée: " + dureeJour + " jours");

        // 3. Insérer dans Prolongement
        Prolongement prolongement = new Prolongement();
        prolongement.setAdherant(ancienPret.getAdherant());
        prolongement.setPret(nouveauPret);
        prolongementService.save(prolongement);

        // 4. Mettre à jour le statut de la demande
        StatutDemande statutDemande = new StatutDemande();
        statutDemande.setDemandeProlongement(demande);
        Statut statutValide = statutService.findById(2).orElse(null); // 2 = Validé
        statutDemande.setStatut(statutValide);
        
        // Utiliser directement le timestamp reçu
        statutDemande.setDateStatut(timestampValidation);
        
        statutDemandeService.save(statutDemande);
        
        System.out.println("Statut VALIDATION créé à : " + timestampValidation);
        System.out.println("=== FIN VALIDATION ===");

        return "redirect:/list-demandes-prolongement";
    }

   @PostMapping("/refuser-demande-prolongement")
   public String refuserDemande(@RequestParam("idDemande") int idDemande) {
       DemandeProlongement demande = demandeProlongementService.findById(idDemande);
       if (demande != null) {
           StatutDemande statutDemande = new StatutDemande();
           statutDemande.setDemandeProlongement(demande);
           Statut statutRefuse = statutService.findById(3).orElse(null); // idStatut = 3 pour "Refusé"
           statutDemande.setStatut(statutRefuse);
           
           // Timestamp actuel avec millisecondes
           statutDemande.setDateStatut(new java.sql.Timestamp(System.currentTimeMillis()));
           
           statutDemandeService.save(statutDemande);
           
           System.out.println("Statut REFUS créé à : " + statutDemande.getDateStatut());
       }
       return "redirect:/list-demandes-prolongement";
   }
   
   @GetMapping("/validation-demande-prolongement")
   public String showValidationForm(@RequestParam("idDemande") int idDemande, Model model) {
       DemandeProlongement demande = demandeProlongementService.findById(idDemande);
       model.addAttribute("demande", demande);
       return "validation-demande-prolongement";
   }
}