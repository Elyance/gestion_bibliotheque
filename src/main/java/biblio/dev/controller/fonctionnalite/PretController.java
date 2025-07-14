package biblio.dev.controller.fonctionnalite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import biblio.dev.entity.livre.*;
import biblio.dev.entity.personne.*;
import biblio.dev.entity.fonctionnalite.*;
import biblio.dev.service.livre.*;
import biblio.dev.service.personne.*;
import biblio.dev.service.regle.*;
import jakarta.servlet.http.HttpServletRequest;
import biblio.dev.service.fonctionnalite.*;

import java.sql.Date;
import java.sql.Timestamp; // Ajoute bien cette importation


@Controller
public class PretController {
    @Autowired
    private LivreService livreService;

    @Autowired
    private TypeAdherantLivreService typeAdherantLivreService;
    
    @Autowired
    private AdherantService adherantService;
    
    @Autowired
    private TypePretService typePretService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PretService pretService;

    @Autowired
    private RegleNbLivreService regleNbLivreService;

    @Autowired
    private RegleDureeService regleDureeService;

    @Autowired 
    private PersonneService personneService;

    @Autowired
    private PenaliteService penaliteService;


    @GetMapping("/preter-livre")
    public String getFormulairePret(@RequestParam("idLivre") int idLivre, Model model) {
        // Récupérer le livre sélectionné
        Optional<Livre> livreOptional = livreService.findById(idLivre);
        
        if (!livreOptional.isPresent()) {
            // Si le livre n'existe pas, rediriger vers la liste
            return "redirect:/list-livres";
        }
        
        Livre livre = livreOptional.get();
        
        // Récupérer les listes nécessaires
        List<Adherant> adherants = adherantService.findAll();
        List<TypePret> typesPret = typePretService.findAll();
        
        // Ajouter les données au modèle
        model.addAttribute("livre", livre);
        model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
        model.addAttribute("adherants", adherants);
        model.addAttribute("typesPret", typesPret);
        
        return "form-pret";
    }

    @GetMapping("/list-livres")
    public String getListeLivres(Model model) {
        List<Livre> livres = livreService.findAll();

        // Log pour diagnostic
        for (Livre livre : livres) {
            System.out.println("Livre: " + livre.getTitre() + 
                              ", Categories: " + 
                              (livre.getCategories() != null ? 
                               livre.getCategories().size() : "null"));
        }

        model.addAttribute("livres", livres);
        return "accueil-admin";
    }

    @GetMapping("/list-prets")
    public String getListePret(Model model) {
        List<Pret> pretsNonRetournes = pretService.findPretsNonRetournes();
        List<Pret> pretsRetournes = pretService.findPretsRetournes();

        model.addAttribute("pretsNonRetournes", pretsNonRetournes);
        model.addAttribute("pretsRetournes", pretsRetournes);
        return "list-pret";
    }


    @PostMapping("/ajouterPret")
    public String ajouterPret(HttpServletRequest request, @SessionAttribute("adminConnecte") Admin admin, Model model) {
       try {
        // Récupérer les paramètres du formulaire
           int idAdherant = Integer.parseInt(request.getParameter("idAdherant"));
           int idTypePret = Integer.parseInt(request.getParameter("idTypePret"));
           int idLivre = Integer.parseInt(request.getParameter("idLivre"));
           String dateDebutStr = request.getParameter("dateDebut");
           String numeroExemplaire = request.getParameter("numeroExemplaire");
        // Vérifier que les paramètres ne sont pas vides
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
           LocalDateTime dateDebut = LocalDateTime.parse(dateDebutStr, formatter);

           Adherant adherant = adherantService.findById(idAdherant)
               .orElseThrow(() -> new RuntimeException("Adhérant introuvable"));
           TypePret typePret = typePretService.findById(idTypePret)
               .orElseThrow(() -> new RuntimeException("Type de prêt introuvable"));
           Exemplaire exemplaire = exemplaireService.getByNumero(numeroExemplaire)
               .orElseThrow(() -> new RuntimeException("Exemplaire introuvable"));
           Livre livre = livreService.findById(idLivre)
               .orElseThrow(() -> new RuntimeException("Livre non existant"));

           // ❌ Vérifie si le livre est autorisé pour ce type d’adhérant à la date du prêt
           Date dateDemande = Date.valueOf(dateDebut.toLocalDate());
           if (!typeAdherantLivreService.isLivreAutorisePourAdherant(adherant, livre, dateDemande)) {
               System.out.println("⛔ Livre non autorisé pour le type d'adhérant (table TypeAdherant_Livre, date) : " + adherant.getTypeAdherant().getNomTypeAdherant());
               model.addAttribute("erreurInsertion", "Ce livre n'est pas autorisé pour cet adhérant à la date demandée.");
               model.addAttribute("adherants", adherantService.findAll());
               model.addAttribute("typesPret", typePretService.findAll());
               model.addAttribute("livre", livre);
               model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
               return "form-pret";
           }

            // 🔍 Vérifie l’âge de l’adhérant par rapport à l’âge limite du livre
           int ageAdherant = personneService.getAgeById(adherant.getIdAdherant());
           
           if (ageAdherant < livre.getAgeLimite()) {
                System.out.println("⛔ Âge insuffisant pour emprunter ce livre.");
                System.out.println("Âge de l’adhérant : " + ageAdherant);
                System.out.println("Âge minimum requis : " + livre.getAgeLimite());

                model.addAttribute("erreurInsertion", "L'âge minimum requis pour ce livre est de " + livre.getAgeLimite() + " ans.");
                model.addAttribute("adherants", adherantService.findAll());
                model.addAttribute("typesPret", typePretService.findAll());
                model.addAttribute("livre", livre);
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                return "form-pret";
            }


            Date dateDebutSql = Date.valueOf(dateDebut.toLocalDate());

           // 📆 Calcule la date de fin selon la règle de durée
           double dureeJour = regleDureeService.getDureePourTypeAdherantAlaDate(adherant.getTypeAdherant(),dateDebutSql);
           LocalDateTime dateFin = dateDebut.plusDays((long) dureeJour);

           System.out.println("Durée autorisée : " + dureeJour + " jours");
           System.out.println("Date début : " + dateDebut);
           System.out.println("Date fin calculée : " + dateFin);

           // 🔒 Vérifie le quota de prêt
           int limite = regleNbLivreService.getLimitePourTypeAdherantAlaDate(adherant.getTypeAdherant(),dateDebutSql);
           int nbPretsActifs = pretService.countPretsEnCoursParAdherant(adherant);

           System.out.println("=== DEBUG PRET ===");
           System.out.println("Adherant: " + adherant.getIdAdherant() + " - " + adherant.getPersonne().getNom());
           System.out.println("Type: " + adherant.getTypeAdherant().getNomTypeAdherant());
           System.out.println("Limite autorisée: " + limite);
           System.out.println("Nombre de prêts actifs: " + nbPretsActifs);

           if (nbPretsActifs >= limite) {
               System.out.println("⚠️ Prêt refusé : quota atteint.");
               model.addAttribute("erreurInsertion", "Limite de " + limite + " prêt(s) atteinte pour cet adhérant.");
               model.addAttribute("adherants", adherantService.findAll());
               model.addAttribute("typesPret", typePretService.findAll());
               model.addAttribute("livre", livre);
               model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
               return "form-pret";
           }

           // 📅 Vérifie l’abonnement pendant toute la durée
           Date sqlDateDebut = Date.valueOf(dateDebut.toLocalDate());
           Date sqlDateFin = Date.valueOf(dateFin.toLocalDate());

           System.out.println("=== DEBUG ABONNEMENT ===");
           System.out.println("Date début prêt : " + sqlDateDebut);
           System.out.println("Date fin prêt : " + sqlDateFin);

           if (!adherantService.isAbonnee(sqlDateDebut, sqlDateFin, adherant)) {
               System.out.println("⛔ L’adhérant n'est pas abonné durant la période !");
               model.addAttribute("erreurInsertion", "Cet adhérant n'est pas abonné durant toute la période de prêt.");
               model.addAttribute("adherants", adherantService.findAll());
               model.addAttribute("typesPret", typePretService.findAll());
               model.addAttribute("livre", livre);
               model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
               return "form-pret";
           }

            // 🔍 Vérifie si l’adhérant est pénalisé durant la période de prêt
           if (penaliteService.isPenaliseAlaDate(adherant,dateDebutSql)) {
                System.out.println("⛔ Adhérant pénalisé durant la période de prêt !");
                model.addAttribute("erreurInsertion", "Cet adhérant est pénalisé pendant la période demandée.");
                model.addAttribute("adherants", adherantService.findAll());
                model.addAttribute("typesPret", typePretService.findAll());
                model.addAttribute("livre", livre);
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                return "form-pret";
            }


           // ✅ Crée et enregistre le prêt
           Pret pret = new Pret();
           pret.setAdherant(adherant);
           pret.setTypePret(typePret);
           pret.setExemplaire(exemplaire);
           pret.setAdmin(admin);
           pret.setDateDebut(Timestamp.valueOf(dateDebut));
           pret.setDateFin(Timestamp.valueOf(dateFin));

           pretService.save(pret);

           model.addAttribute("succesInsertion", "Prêt enregistré avec succès.");
           return "redirect:/list-livres";

       } catch (Exception e) {
           e.printStackTrace();
           model.addAttribute("erreurInsertion", "Erreur lors de l'enregistrement du prêt : " + e.getMessage());
           return "form-pret";
       }
    }   



}