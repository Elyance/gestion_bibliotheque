package biblio.dev.controller.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.personne.Admin;

import biblio.dev.service.fonctionnalite.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ReservationValidationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/validation-reservations")
    public String showNonValideReservations(Model model) {
        model.addAttribute("reservations", reservationService.findNonValide());
        return "validation-reservations";
    }

    @PostMapping("/valider-reservation")
    public String validerReservation(@RequestParam("idReservation") int idReservation,
                                     @SessionAttribute("adminConnecte") Admin admin,
                                     Model model) {
        Reservation reservation = reservationService.findById(idReservation).orElse(null);
        if (reservation != null) {
            reservation.setAdmin(admin);
            reservationService.save(reservation);
            model.addAttribute("success", "Réservation validée avec succès.");
        } else {
            model.addAttribute("error", "Réservation introuvable.");
        }
        // Rafraîchir la liste après validation
        java.util.List<biblio.dev.entity.fonctionnalite.Reservation> reservations = reservationService.findNonValide();
        for (biblio.dev.entity.fonctionnalite.Reservation res : reservations) {
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) res.getAdherant().getPersonne().getNom();
            if (res.getExemplaire() != null) res.getExemplaire().getNumero();
        }
        model.addAttribute("reservations", reservations);
        return "validation-reservations";
    }
}
