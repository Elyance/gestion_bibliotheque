package biblio.dev.controller.affichage;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.service.fonctionnalite.PretService;
import biblio.dev.service.fonctionnalite.DemandeProlongementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class ListPretAdherantController {

    @Autowired
    private PretService pretService;

    @Autowired
    private DemandeProlongementService demandeProlongementService;

    @GetMapping("/mes-prets")
    public String listPretsAdherant(@SessionAttribute("adherantConnecte") Adherant adherant, Model model) {
        List<Pret> prets = pretService.findByAdherant(adherant);
        model.addAttribute("prets", prets);
        model.addAttribute("demandesProlongement", demandeProlongementService.findByAdherant(adherant));
        return "list-prets-adherant";
    }
}
