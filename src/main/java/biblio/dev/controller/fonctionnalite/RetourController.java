package biblio.dev.controller.fonctionnalite;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.service.fonctionnalite.PretService;
import biblio.dev.service.fonctionnalite.PenaliteService;
import biblio.dev.service.fonctionnalite.PenaliteQuotaService;
import jakarta.servlet.http.HttpServletRequest;
import biblio.dev.entity.fonctionnalite.Penalite;

import biblio.dev.entity.fonctionnalite.Retour;
import biblio.dev.service.fonctionnalite.RetourService;
import biblio.dev.service.fonctionnalite.JourFerierService;


@Controller
public class RetourController {
    @Autowired
    private PretService pretService;
    @Autowired
    private RetourService retourService;

    @Autowired
    private PenaliteService penaliteService;

    @Autowired
    private PenaliteQuotaService penaliteQuotaService;

    @Autowired
    private JourFerierService jourFerierService;

    @GetMapping("/retourner/{idPret}")
    public String getFromulaire(@PathVariable("idPret") int idPret, Model model) {
        System.out.println("=== GET FORMULAIRE RETOUR ===");
        System.out.println("ID Prêt: " + idPret);
        
        // Vérifier si le prêt existe
        Optional<Pret> pretOptional = pretService.findById(idPret);
        if (!pretOptional.isPresent()) {
            model.addAttribute("erreur", "Prêt introuvable.");
            return "form-retour";
        }
        
        // Vérifier si le prêt n'est pas déjà retourné
        Optional<Retour> retourExistant = retourService.findByPret(pretOptional.get());
        if (retourExistant.isPresent()) {
            model.addAttribute("erreur", "Ce prêt a déjà été retourné le " + retourExistant.get().getDateRetour());
            return "form-retour";
        }
        
        model.addAttribute("idPret", idPret);
        return "form-retour";
    }

    @PostMapping("/retourner")
    public String insertRetour(HttpServletRequest request, Model model) {
        System.out.println("=== POST RETOUR ===");
        
        try {
            int idPret = Integer.parseInt(request.getParameter("idPret"));
            Date dateRetour = Date.valueOf(request.getParameter("dateRetour"));
            
            System.out.println("ID Prêt: " + idPret);
            System.out.println("Date de retour: " + dateRetour);

            // Vérification jour férié
            boolean isJourFerier = jourFerierService.isJourFerier(dateRetour);
            System.out.println("Est jour férié: " + isJourFerier);
            
            if (isJourFerier) {
                System.out.println("ERREUR: Retour impossible le jour férié");
                model.addAttribute("erreur", "Impossible de retourner un livre un jour férié.");
                model.addAttribute("idPret", idPret);
                return "form-retour";
            }

            Pret pret = pretService.findById(idPret).orElse(null);
            System.out.println("Prêt trouvé: " + (pret != null));
            
            if (pret != null) {
                // Vérifier si le prêt n'est pas déjà retourné
                Optional<Retour> retourExistant = retourService.findByPret(pret);
                if (retourExistant.isPresent()) {
                    System.out.println("ERREUR: Prêt déjà retourné");
                    model.addAttribute("erreur", "Ce prêt a déjà été retourné le " + retourExistant.get().getDateRetour());
                    model.addAttribute("idPret", idPret);
                    return "form-retour";
                }
                
                System.out.println("Adhérant: " + pret.getAdherant().getPersonne().getNom());
                System.out.println("Livre: " + pret.getExemplaire().getLivre().getTitre());
                System.out.println("Date fin prêt: " + pret.getDateFin());
                System.out.println("Type adhérant: " + pret.getAdherant().getTypeAdherant().getNomTypeAdherant());
                
                Retour retour = new Retour();
                retour.setPret(pret);
                retour.setDateRetour(dateRetour);
                retourService.save(retour);
                System.out.println("Retour sauvegardé avec succès");

                // Vérifier le retard et pénaliser selon le type d'adhérant
                boolean enRetard = dateRetour.after(pret.getDateFin());
                System.out.println("En retard: " + enRetard);
                
                if (enRetard) {
                    int nbJourPenalite = penaliteQuotaService.getNbJourPenalite(pret.getAdherant().getTypeAdherant());
                    System.out.println("Nombre de jours pénalité: " + nbJourPenalite);
                    
                    Penalite dernierePenalite = penaliteService.getDernierePenalite(pret.getAdherant());
                    System.out.println("Dernière pénalité: " + (dernierePenalite != null ? dernierePenalite.getDate() : "Aucune"));
                    
                    Date datePenalite;

                    if (dernierePenalite != null) {
                        Date dateFinDerniere = new Date(dernierePenalite.getDate().getTime());
                        Date nouvelleDate = new Date(dateFinDerniere.getTime() + nbJourPenalite * 24L * 60L * 60L * 1000L);
                        System.out.println("Date fin dernière pénalité: " + dateFinDerniere);
                        System.out.println("Nouvelle date calculée: " + nouvelleDate);
                        
                        if (dateRetour.before(nouvelleDate)) {
                            datePenalite = nouvelleDate;
                            System.out.println("Pénalité étendue à: " + datePenalite);
                        } else {
                            datePenalite = dateRetour;
                            System.out.println("Pénalité à partir de: " + datePenalite);
                        }
                    } else {
                        datePenalite = dateRetour;
                        System.out.println("Première pénalité à partir de: " + datePenalite);
                    }

                    Penalite penalite = new Penalite();
                    penalite.setPret(pret);
                    penalite.setDate(datePenalite);
                    penalite.setNbJourPenalite(nbJourPenalite);
                    penaliteService.save(penalite);
                    System.out.println("Pénalité sauvegardée: " + nbJourPenalite + " jours jusqu'au " + datePenalite);
                }

                System.out.println("=== RETOUR TERMINÉ AVEC SUCCÈS ===");
                return "redirect:/list-prets";
            } else {
                System.out.println("ERREUR: Prêt introuvable");
                model.addAttribute("erreur", "Prêt introuvable.");
                model.addAttribute("idPret", idPret);
                return "form-retour";
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());
            model.addAttribute("erreur", "Erreur lors du retour : " + e.getMessage());
            model.addAttribute("idPret", request.getParameter("idPret"));
            return "form-retour";
        }
    }
}
