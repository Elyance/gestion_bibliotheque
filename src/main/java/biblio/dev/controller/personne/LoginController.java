package biblio.dev.controller.personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import biblio.dev.entity.personne.*;
import biblio.dev.service.personne.*;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private PersonneService personneService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdherantService adherantService;

    @GetMapping("/login-admin")
    public String formulaireLoginAdmin() {
        return "form-login";
    }

    @GetMapping("/login-adherant")
    public String formulaireLoginAdherent() {
        return "form-login";
    }

    @PostMapping("/login-check")
    public String checkLogin(HttpServletRequest request, org.springframework.ui.Model model) {
        Personne personne = personneService.login(request.getParameter("mail"), request.getParameter("password"));
        if (personne == null) {
            model.addAttribute("message", "Identifiants invalides");
            return "form-login";
        }
        // Mettre la personne connectée en session
        request.getSession().setAttribute("personneConnectee", personne);
        Admin admin = adminService.findByPersonne(personne);
        if (admin != null) {
            model.addAttribute("message", "Bienvenue, administrateur !");
            request.getSession().setAttribute("adminConnecte", admin);
            return "admin-template";
        }
        Adherant adherant = adherantService.findByPersonne(personne);
        if (adherant != null) {
            model.addAttribute("message", "Bienvenue, adhérant !");
            request.getSession().setAttribute("adherantConnecte", adherant);
            return "adherant-template";
        }
        model.addAttribute("message", "Aucun rôle trouvé pour cet utilisateur");
        return "form-login";
    }
}
