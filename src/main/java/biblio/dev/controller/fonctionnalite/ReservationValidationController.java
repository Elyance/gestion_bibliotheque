package biblio.dev.controller.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.description.Statut;
import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import biblio.dev.service.fonctionnalite.ReservationService;
import biblio.dev.service.description.StatutService;
import biblio.dev.service.description.StatutReservationService;

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
    
    @Autowired
    private StatutService statutService;
    
    @Autowired
    private StatutReservationService statutReservationService;

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
            // Mettre à jour l'admin qui a validé
            reservation.setAdmin(admin);
            reservationService.save(reservation);
            
            // Changer le statut vers "Validé" (supposons que l'ID 2 = Validé)
            Statut statutValide = statutService.findById(2).orElse(null);
            if (statutValide != null) {
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statutValide.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutValide);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            
            model.addAttribute("success", "Réservation validée avec succès.");
        } else {
            model.addAttribute("error", "Réservation introuvable.");
        }
        
        // Rafraîchir la liste après validation
        refreshReservationsList(model);
        return "validation-reservations";
    }

    @PostMapping("/refuser-reservation")
    public String refuserReservation(@RequestParam("idReservation") int idReservation,
                                     @SessionAttribute("adminConnecte") Admin admin,
                                     Model model) {
        Reservation reservation = reservationService.findById(idReservation).orElse(null);
        if (reservation != null) {
            // Mettre à jour l'admin qui a refusé
            reservation.setAdmin(admin);
            reservationService.save(reservation);
            
            // Changer le statut vers "Refusé" (supposons que l'ID 3 = Refusé)
            Statut statutRefuse = statutService.findById(3).orElse(null);
            if (statutRefuse != null) {
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statutRefuse.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutRefuse);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            
            model.addAttribute("success", "Réservation refusée avec succès.");
        } else {
            model.addAttribute("error", "Réservation introuvable.");
        }
        
        // Rafraîchir la liste après refus
        refreshReservationsList(model);
        return "validation-reservations";
    }

    private void refreshReservationsList(Model model) {
        java.util.List<biblio.dev.entity.fonctionnalite.Reservation> reservations = reservationService.findNonValide();
        for (biblio.dev.entity.fonctionnalite.Reservation res : reservations) {
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) {
                res.getAdherant().getPersonne().getNom();
            }
            if (res.getExemplaire() != null) {
                res.getExemplaire().getNumero();
            }
        }
        model.addAttribute("reservations", reservations);
    }
}