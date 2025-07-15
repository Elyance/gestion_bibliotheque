package biblio.dev.controller.affichage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.livre.Livre;
import biblio.dev.service.livre.LivreService;
import biblio.dev.service.livre.TypeAdherantLivreService;

@Controller
public class AccueilAdherantController {
    @Autowired
    private LivreService livreService;

    @Autowired
    private TypeAdherantLivreService typeAdherantLivreService;

    @GetMapping("/list-livre-adherant")
    public String accueilAdherant(Model model, @SessionAttribute("adherantConnecte") Adherant adherant) {
        List<Livre> livres = livreService.findAll();
        java.util.Map<Integer, Boolean> autorisations = new java.util.HashMap<>();
        java.util.Date today = new java.util.Date();
        for (Livre livre : livres) {
            boolean autorise = typeAdherantLivreService.isLivreAutorisePourAdherant(adherant, livre, today);
            autorisations.put(livre.getIdLivre(), autorise);
        }
        model.addAttribute("livres", livres);
        model.addAttribute("autorisations", autorisations);
        return "accueil-adherant";
    }
}
