package biblio.dev.controller.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import biblio.dev.entity.fonctionnalite.Abonnement;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.service.fonctionnalite.AbonnementService;

import java.sql.Date;
import java.text.SimpleDateFormat;

import biblio.dev.service.personne.AdherantService;

@Controller
public class AbonnementController {
    @Autowired
    private AbonnementService abonnementService;

    @GetMapping("/abonnement-adherent")
    public String formulaireAbonnementAdherent(Model model) {
        return "abonnement-adherant";
    }

    @PostMapping("/doAbonnement")
    public String doAbonnement(HttpServletRequest request,Model model) {
        try {
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDateDebut = sdf.parse(request.getParameter("dateDebut"));
            java.util.Date utilDateFin = sdf.parse(request.getParameter("dateFin"));
            java.sql.Date dateDebut = new java.sql.Date(utilDateDebut.getTime());
            java.sql.Date dateFin = new java.sql.Date(utilDateFin.getTime());

            if (dateDebut.after(dateFin)) {
                model.addAttribute("message", "La date de début doit être antérieure à la date de fin.");
                model.addAttribute("success", false);
                return "abonnement-adherant";
            }

            Abonnement abonnement = new Abonnement();
            abonnement.setDateDebut(dateDebut);
            abonnement.setDateFin(dateFin);
            abonnement.setAdherant((Adherant) request.getSession().getAttribute("adherantConnecte"));



            abonnementService.save(abonnement);
            
            model.addAttribute("message", "Abonnement créé avec succès !");
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("message", "Erreur lors de l'insertion de l'abonnement : " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "abonnement-adherant";
    }

}
