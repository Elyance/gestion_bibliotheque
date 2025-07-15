package biblio.dev.controller.affichage;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.service.fonctionnalite.ReservationService;
import biblio.dev.entity.description.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ListReservationAdherantController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/mes-reservations")
    public String listReservationsAdherant(
            @SessionAttribute("adherantConnecte") Adherant adherant,
            Model model) {

        // Récupère les réservations de l'adhérant
        List<Reservation> reservations = reservationService.findByAdherant(adherant);

        // Map des statuts actuels pour chaque réservation
        Map<Integer, Statut> statutsActuels = reservations.stream()
            .collect(Collectors.toMap(
                Reservation::getIdReservation,
                r -> r.getStatut() != null ? (Statut) r.getStatut() : new Statut() // ou null si tu préfères
            ));

        model.addAttribute("reservations", reservations);
        model.addAttribute("statutsActuels", statutsActuels);

        return "list-reservation-adherant";
    }
}