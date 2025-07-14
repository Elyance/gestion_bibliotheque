package biblio.dev.controller.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.service.fonctionnalite.PretService;
import jakarta.servlet.http.HttpServletRequest;

import biblio.dev.entity.fonctionnalite.Retour;
import biblio.dev.service.fonctionnalite.RetourService;


@Controller
public class RetourController {
    @Autowired
    private PretService pretService;
    @Autowired
    private RetourService retourService;

    @GetMapping("/retourner/{idPret}")
    public String getFromulaire(@PathVariable("idPret") int idPret, Model model) {
        model.addAttribute("idPret", idPret);
        return "form-retour";
    }

    @PostMapping("/retourner")
    public String insertRetour(HttpServletRequest request) {
        int idPret = Integer.parseInt(request.getParameter("idPret"));

        Pret pret = pretService.findById(idPret).orElse(null);

        if (pret != null) {
            Retour retour = new Retour();
            retour.setPret(pret);
            retour.setDateRetour(new java.util.Date());
            retourService.save(retour);

            return "redirect:/list-prets"; // Redirige vers la liste des prêts de l'adhérant
        } else {
            request.setAttribute("erreur", "Prêt introuvable.");
            return "form-retour";
        }
    }
}
