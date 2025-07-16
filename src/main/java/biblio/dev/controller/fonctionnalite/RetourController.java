package biblio.dev.controller.fonctionnalite;

import java.sql.Date;

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
        model.addAttribute("idPret", idPret);
        return "form-retour";
    }

    @PostMapping("/retourner")
    public String insertRetour(HttpServletRequest request) {
        int idPret = Integer.parseInt(request.getParameter("idPret"));
        Date dateRetour = Date.valueOf(request.getParameter("dateRetour"));

        // Vérification jour férié
        if (jourFerierService.isJourFerier(dateRetour)) {
            request.setAttribute("erreur", "Impossible de retourner un livre un jour férié.");
            return "form-retour";
        }

        Pret pret = pretService.findById(idPret).orElse(null);

        if (pret != null) {
            Retour retour = new Retour();
            retour.setPret(pret);
            retour.setDateRetour(dateRetour);
            retourService.save(retour);

            // Vérifier le retard et pénaliser selon le type d'adhérant
            if (dateRetour.after(pret.getDateFin())) {
                int nbJourPenalite = penaliteQuotaService.getNbJourPenalite(pret.getAdherant().getTypeAdherant());
                Penalite dernierePenalite = penaliteService.getDernierePenalite(pret.getAdherant());
                Date datePenalite;

                if (dernierePenalite != null) {
                    Date dateFinDerniere = new Date(dernierePenalite.getDate().getTime());
                    Date nouvelleDate = new Date(dateFinDerniere.getTime() + nbJourPenalite * 24L * 60L * 60L * 1000L);
                    if (dateRetour.before(nouvelleDate)) {
                        datePenalite = nouvelleDate;
                    } else {
                        datePenalite = dateRetour;
                    }
                } else {
                    datePenalite = dateRetour;
                }

                Penalite penalite = new Penalite();
                penalite.setPret(pret);
                penalite.setDate(datePenalite);
                penalite.setNbJourPenalite(nbJourPenalite);
                penaliteService.save(penalite);
            }

            return "redirect:/list-prets";
        } else {
            request.setAttribute("erreur", "Prêt introuvable.");
            return "form-retour";
        }
    }
}
