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
import java.util.stream.Collectors;

import biblio.dev.entity.livre.Livre;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.fonctionnalite.TypePret;
import biblio.dev.entity.fonctionnalite.Reservation;
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
    @Autowired private biblio.dev.service.fonctionnalite.ReservationService reservationService;
    @Autowired private biblio.dev.service.fonctionnalite.RetourService retourService;

    // Affiche le formulaire de prêt
    @GetMapping("/preter-livre")
    public String getFormulairePret(@RequestParam("idLivre") int idLivre, Model model) {
        Optional<Livre> livreOptional = livreService.findById(idLivre);
        if (!livreOptional.isPresent()) return "redirect:/list-livres";
        Livre livre = livreOptional.get();
        
        // Récupérer tous les exemplaires du livre
        List<Exemplaire> tousExemplaires = exemplaireService.getExemplairesByLivre(livre);
        
        // Filtrer pour ne garder que les exemplaires disponibles (pas en prêt)
        List<Exemplaire> exemplairesDisponibles = tousExemplaires.stream()
            .filter(exemplaire -> {
                // Vérifier si l'exemplaire n'est pas en prêt actuel
                List<Pret> pretsExemplaire = pretService.findByExemplaire(exemplaire);
                // Un exemplaire est DISPONIBLE s'il n'a AUCUN prêt non retourné
                return pretsExemplaire.stream()
                    .allMatch(p -> retourService.findByPret(p).isPresent()); // Tous les prêts sont retournés
            })
            .collect(Collectors.toList());
        
        System.out.println("=== DEBUG EXEMPLAIRES DISPONIBLES ===");
        System.out.println("Livre: " + livre.getTitre());
        System.out.println("Tous exemplaires: " + tousExemplaires.size());
        System.out.println("Exemplaires disponibles: " + exemplairesDisponibles.size());
        
        for (Exemplaire ex : tousExemplaires) {
            List<Pret> pretsEx = pretService.findByExemplaire(ex);
            long pretsNonRetournes = pretsEx.stream()
                .filter(p -> retourService.findByPret(p).isEmpty())
                .count();
            System.out.println("Exemplaire " + ex.getNumero() + " - Prêts non retournés: " + pretsNonRetournes);
        }
        
        model.addAttribute("livre", livre);
        model.addAttribute("exemplaires", exemplairesDisponibles);
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
            System.out.println("Début ajout prêt");
            int idAdherant = Integer.parseInt(request.getParameter("idAdherant"));
            int idTypePret = Integer.parseInt(request.getParameter("idTypePret"));
            int idLivre = Integer.parseInt(request.getParameter("idLivre"));
            String dateDebutStr = request.getParameter("dateDebut");
            String numeroExemplaire = request.getParameter("numeroExemplaire");
            System.out.println("Paramètres reçus : idAdherant=" + idAdherant + ", idTypePret=" + idTypePret + ", idLivre=" + idLivre + ", dateDebut=" + dateDebutStr + ", numeroExemplaire=" + numeroExemplaire);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateDebut = LocalDateTime.parse(dateDebutStr, formatter);

            Adherant adherant = adherantService.findById(idAdherant).orElseThrow(() -> new RuntimeException("Adhérant introuvable"));
            TypePret typePret = typePretService.findById(idTypePret).orElseThrow(() -> new RuntimeException("Type de prêt introuvable"));
            Exemplaire exemplaire = exemplaireService.getByNumero(numeroExemplaire).orElseThrow(() -> new RuntimeException("Exemplaire introuvable"));
            Livre livre = livreService.findById(idLivre).orElseThrow(() -> new RuntimeException("Livre non existant"));
            System.out.println("Entités récupérées : adherant=" + adherant.getNumeroAdherant() + ", typePret=" + typePret.getNomType() + ", exemplaire=" + exemplaire.getNumero() + ", livre=" + livre.getTitre());



            Pret pret = pretService.verifierEtConstruirePret(adherant, exemplaire, typePret, admin, model, dateDebut);
            System.out.println("Pret construit : " + (pret != null ? "OK" : "NULL"));

            // Vérification disponibilité exemplaire
            List<Pret> pretsExemplaire = pretService.findByExemplaire(exemplaire);
            boolean exemplaireEnPret = pretsExemplaire.stream().anyMatch(p -> retourService.findByPret(p).isEmpty());
            List<Reservation> reservations = reservationService.findAll();
            boolean exemplaireReserve = reservations.stream().anyMatch(r -> r.getExemplaire().getIdExemplaire() == exemplaire.getIdExemplaire() && r.getStatut() != null && r.getStatut().getIdStatut() == 2);
            System.out.println("Exemplaire en prêt : " + exemplaireEnPret + ", Exemplaire réservé : " + exemplaireReserve);

            if (exemplaireEnPret) {
                System.out.println("Exemplaire non disponible");
                model.addAttribute("error", "Cet exemplaire n'est pas disponible : il est déjà réservé ou en prêt.");
                model.addAttribute("adherants", adherantService.findAll());
                model.addAttribute("typesPret", typePretService.findAll());
                model.addAttribute("livre", livre);
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                return "form-pret";
            }

            if (pret == null) {
                System.out.println("Pret null après vérification");
                model.addAttribute("adherants", adherantService.findAll());
                model.addAttribute("typesPret", typePretService.findAll());
                model.addAttribute("livre", livre);
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                return "form-pret";
            }
            pretService.save(pret);
            System.out.println("Prêt enregistré avec succès.");
            model.addAttribute("succesInsertion", "Prêt enregistré avec succès.");
            return "redirect:/list-prets";
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement du prêt : " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("erreurInsertion", "Erreur lors de l'enregistrement du prêt : " + e.getMessage());
            return "form-pret";
        }
    }
}