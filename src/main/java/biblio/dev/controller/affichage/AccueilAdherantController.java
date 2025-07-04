package biblio.dev.controller.affichage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import biblio.dev.entity.livre.Livre;
import biblio.dev.service.livre.LivreService;

@Controller
public class AccueilAdherantController {
    @Autowired
    private LivreService livreService;

    @GetMapping("/accueil-adherant")
    public String accueilAdherant(Model model) {
        List<Livre> livres = livreService.findAll();
        model.addAttribute("livres", livres);
        return "accueil-adherant";
    }
}
