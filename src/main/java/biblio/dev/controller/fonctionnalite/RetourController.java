package biblio.dev.controller.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.service.fonctionnalite.PretService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RetourController {
    @Autowired
    private PretService pretService;

    @GetMapping("/retourner/{idPret}")
    public String getFromulaire(HttpServletRequest request,Model model) {
        model.addAttribute("idPret",request.getParameter("idPret"));
        return "form-retour";
    }

    @PostMapping("/retourner")
    public String insertRetour(HttpServletRequest request) {
        return "";
    }
}
