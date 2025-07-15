package biblio.dev.controller.fonctionnalite;

// Imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import biblio.dev.entity.livre.Livre;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.fonctionnalite.TypePret;
import biblio.dev.service.livre.LivreService;
import biblio.dev.service.livre.ExemplaireService;
import biblio.dev.service.personne.AdherantService;
import biblio.dev.service.fonctionnalite.TypePretService;
import biblio.dev.service.fonctionnalite.PretService;

@Controller
public class PretController {
    @Autowired private LivreService livreService;
    @Autowired private AdherantService adherantService;
    @Autowired private TypePretService typePretService;
    @Autowired private ExemplaireService exemplaireService;
    @Autowired private PretService pretService;

    // Affiche le formulaire de prêt
    @GetMapping("/preter-livre")
    public String getFormulairePret(@RequestParam("idLivre") int idLivre, Model model) {
        Optional<Livre> livreOptional = livreService.findById(idLivre);
        if (!livreOptional.isPresent()) return "redirect:/list-livres";
        Livre livre = livreOptional.get();
        model.addAttribute("livre", livre);
        model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
        model.addAttribute("adherants", adherantService.findAll());
        model.addAttribute("typesPret", typePretService.findAll());
        return "form-pret";
    }

    // Liste des livres
    @GetMapping("/list-livres")
    public String getListeLivres(Model model) {
        List<Livre> livres = livreService.findAll();
        model.addAttribute("livres", livres);
        return "accueil-admin";
    }

    // Liste des prêts
    @GetMapping("/list-prets")
    public String getListePret(Model model) {
        model.addAttribute("pretsNonRetournes", pretService.findPretsNonRetournes());
        model.addAttribute("pretsRetournes", pretService.findPretsRetournes());
        return "list-pret";
    }

    // Ajout d'un prêt
    @PostMapping("/ajouterPret")
    public String ajouterPret(HttpServletRequest request, @SessionAttribute("adminConnecte") Admin admin, Model model) {
        try {
            int idAdherant = Integer.parseInt(request.getParameter("idAdherant"));
            int idTypePret = Integer.parseInt(request.getParameter("idTypePret"));
            int idLivre = Integer.parseInt(request.getParameter("idLivre"));
            String dateDebutStr = request.getParameter("dateDebut");
            String numeroExemplaire = request.getParameter("numeroExemplaire");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateDebut = LocalDateTime.parse(dateDebutStr, formatter);

            Adherant adherant = adherantService.findById(idAdherant).orElseThrow(() -> new RuntimeException("Adhérant introuvable"));
            TypePret typePret = typePretService.findById(idTypePret).orElseThrow(() -> new RuntimeException("Type de prêt introuvable"));
            Exemplaire exemplaire = exemplaireService.getByNumero(numeroExemplaire).orElseThrow(() -> new RuntimeException("Exemplaire introuvable"));
            Livre livre = livreService.findById(idLivre).orElseThrow(() -> new RuntimeException("Livre non existant"));
            Pret pret = pretService.verifierEtConstruirePret(adherant, exemplaire, typePret, admin, model);
            
            if (pret == null) {
                model.addAttribute("adherants", adherantService.findAll());
                model.addAttribute("typesPret", typePretService.findAll());
                model.addAttribute("livre", livre);
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                return "form-pret";
            }
            pretService.save(pret);
            model.addAttribute("succesInsertion", "Prêt enregistré avec succès.");
            return "redirect:/list-prets";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("erreurInsertion", "Erreur lors de l'enregistrement du prêt : " + e.getMessage());
            return "form-pret";
        }
    }
}