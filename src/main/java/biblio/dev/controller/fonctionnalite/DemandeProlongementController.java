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
import java.util.Map;
import java.util.stream.Collectors;

import biblio.dev.entity.personne.Admin;



@Controller
public class DemandeProlongementController {

    @Autowired
    private PretService pretService;
    @Autowired
    private DemandeProlongementService demandeProlongementService;
    @Autowired
    private StatutDemandeService statutDemandeService;
    @Autowired
    private StatutService statutService;

    @GetMapping("/demande-prolongement")
    public String formDemandeProlongement(@RequestParam("idPret") int idPret, Model model) {
        Pret pret = pretService.findById(idPret).orElse(null);
        model.addAttribute("pret", pret);
        model.addAttribute("currentDate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "demande-prolongement";
    }

    @PostMapping("/demande-prolongement")
    public String submitDemandeProlongement(
            @RequestParam("idPret") int idPret,
            @RequestParam("nbJourDemande") int nbJourDemande,
            @RequestParam("dateDemande") String dateDemandeStr,
            @SessionAttribute("adherantConnecte") Adherant adherant,
            Model model) {
        Pret pret = pretService.findById(idPret).orElse(null);
        DemandeProlongement demande = new DemandeProlongement();
        demande.setPret(pret);
        demande.setAdherant(adherant);
        demande.setNbJourDemande(nbJourDemande);
        demande.setDateDemande(java.sql.Date.valueOf(dateDemandeStr));
        demandeProlongementService.save(demande);

        // Ajout du statut "En attente" (idStatut = 1)
        StatutDemande statutDemande = new StatutDemande();
        statutDemande.setDemandeProlongement(demande);
        Statut statutEnAttente = statutService.findById(1).orElse(null);
        statutDemande.setStatut(statutEnAttente);
        statutDemande.setDateStatut(new Date());
        statutDemandeService.save(statutDemande);

        model.addAttribute("message", "Demande de prolongement envoyée !");
        return "redirect:/mes-prets";
    }

    @GetMapping("/list-demandes-prolongement")
    public String listDemandesProlongementAdmin(Model model) {
        List<DemandeProlongement> toutesDemandes = demandeProlongementService.findAll();

        List<DemandeProlongement> enAttente = new ArrayList<>();
        List<DemandeProlongement> valides = new ArrayList<>();
        List<DemandeProlongement> refusees = new ArrayList<>();

        for (DemandeProlongement demande : toutesDemandes) {
            StatutDemande statutActuel = statutDemandeService.getStatutActuel(demande);
            if (statutActuel != null) {
                int idStatut = statutActuel.getStatut().getIdStatut();
                if (idStatut == 1) { // En attente
                    enAttente.add(demande);
                } else if (idStatut == 2) { // Validé
                    valides.add(demande);
                } else if (idStatut == 3) { // Refusé
                    refusees.add(demande);
                }
            }
        }

        model.addAttribute("demandesEnAttente", enAttente);
        model.addAttribute("demandesValidees", valides);
        model.addAttribute("demandesRefusees", refusees);

        return "list-demandes-prolongement";
    }

   @PostMapping("/valider-demande-prolongement")
   public String validerDemande(
           @RequestParam("idDemande") int idDemande,
           @RequestParam("dateValidation") String dateValidationStr,
           @SessionAttribute("adminConnecte") Admin admin,
           Model model
   ) {
       DemandeProlongement demande = demandeProlongementService.findById(idDemande);
       if (demande != null) {
           Pret ancienPret = demande.getPret();
           if (ancienPret == null) {
               model.addAttribute("error", "Aucun prêt associé à cette demande de prolongement.");
               model.addAttribute("demande", demande);
               return "validation-demande-prolongement";
           }
           java.time.LocalDateTime dateDebut = java.sql.Date.valueOf(dateValidationStr).toLocalDate().atStartOfDay();
           Pret nouveauPret = new Pret();
           nouveauPret.setAdmin(admin);
           nouveauPret.setAdherant(demande.getAdherant());
           nouveauPret.setExemplaire(ancienPret.getExemplaire());
           nouveauPret.setTypePret(ancienPret.getTypePret());
           nouveauPret.setDateDebut(java.sql.Date.valueOf(dateValidationStr));
           java.util.Calendar cal = java.util.Calendar.getInstance();
           cal.setTime(ancienPret.getDateFin());
           cal.add(java.util.Calendar.DAY_OF_MONTH, demande.getNbJourDemande());
           nouveauPret.setDateFin(new java.sql.Date(cal.getTimeInMillis()));
           nouveauPret.setRetour(null);
           
           if (nouveauPret == null) {
               model.addAttribute("demande", demande);
               return "validation-demande-prolongement";
           }
           pretService.save(nouveauPret);

           StatutDemande statutDemande = new StatutDemande();
           statutDemande.setDemandeProlongement(demande);
           Statut statutValide = statutService.findById(2).orElse(null);
           statutDemande.setStatut(statutValide);
           statutDemande.setDateStatut(java.sql.Date.valueOf(dateValidationStr));
           statutDemandeService.save(statutDemande);
       }
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
           statutDemande.setDateStatut(new Date());
           statutDemandeService.save(statutDemande);
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