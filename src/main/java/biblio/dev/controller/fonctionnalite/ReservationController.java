package biblio.dev.controller.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.service.fonctionnalite.ReservationService;
import biblio.dev.service.livre.ExemplaireService;
import biblio.dev.service.livre.LivreService;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.livre.Livre;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.description.Statut;
import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import biblio.dev.service.description.StatutService;
import biblio.dev.service.description.StatutReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Date;

@Controller
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private StatutService statutService;
    @Autowired
    private StatutReservationService statutReservationService;

    @GetMapping("/reserver-livre")
    public String showReservationForm(@RequestParam(value = "idLivre", required = false) Integer idLivre, Model model) {
        if (idLivre != null) {
            Livre livre = livreService.findById(idLivre).orElse(null);
            if (livre != null) {
                model.addAttribute("exemplaires", exemplaireService.getExemplairesByLivre(livre));
                model.addAttribute("livre", livre);
            } else {
                model.addAttribute("exemplaires", exemplaireService.findAll());
            }
        } else {
            model.addAttribute("exemplaires", exemplaireService.findAll());
        }
        return "reserver-livre";
    }

    @PostMapping("/do-reservation")
    public String reserverLivre(HttpServletRequest request,
                                @SessionAttribute("adherantConnecte") Adherant adherant,
                                Model model) {
        String numeroExemplaire = request.getParameter("numeroExemplaire");
        String dateReservationStr = request.getParameter("dateReservation");
       //  String idLivreStr = request.getParameter("idLivre");
        java.util.List<Exemplaire> exemplaires = exemplaireService.findAll();
        Exemplaire exemplaire = exemplaires.stream()
                .filter(e -> e.getNumero().equals(numeroExemplaire))
                .findFirst().orElse(null);
        if (exemplaire == null) {
            model.addAttribute("error", "Numéro d'exemplaire introuvable.");
            model.addAttribute("exemplaires", exemplaires);
            return "reserver-livre";
        }
        try {
            Date dateReservation = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateReservationStr);
            Reservation reservation = new Reservation();
            reservation.setAdherant(adherant);
            reservation.setExemplaire(exemplaire);
            reservation.setDateReservation(dateReservation);
            reservation.setDate_(new java.util.Date());
            reservationService.save(reservation);
            // Ajout du statut initial "En attente"
            Statut statut = statutService.findById(1).orElse(null); // 1 = En attente
            if (statut != null) {
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statut.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statut);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            model.addAttribute("success", "Réservation enregistrée avec succès.");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la réservation.");
        }
        model.addAttribute("exemplaires", exemplaires);
        return "reserver-livre";
    }

    @GetMapping("/success")
    public String reservationSuccess() {
        return "reservation-success";
    }

    
}
