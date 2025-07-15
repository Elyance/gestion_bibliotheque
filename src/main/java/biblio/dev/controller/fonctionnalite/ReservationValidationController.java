package biblio.dev.controller.fonctionnalite;


import biblio.dev.service.regle.RegleDureeService;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.service.fonctionnalite.PretService;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.description.Statut;
import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import biblio.dev.service.fonctionnalite.ReservationService;
import biblio.dev.service.description.StatutService;
import biblio.dev.service.description.StatutReservationService;
import biblio.dev.entity.fonctionnalite.TypePret;
import biblio.dev.service.fonctionnalite.TypePretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;


@Controller
public class ReservationValidationController {
    @Autowired
    private RegleDureeService regleDureeService;
    @Autowired
    private PretService pretService;
    @Autowired
    private TypePretService typePretService;
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private StatutService statutService;
    
    @Autowired
    private StatutReservationService statutReservationService; 



    @GetMapping("/validation-reservations")
    public String showReservations(Model model) {
        List<Reservation> reservationsNonValides = reservationService.findNonValide();
        List<Reservation> reservationsValides = reservationService.findValide();
        List<Reservation> reservationsTraites = reservationService.findTraitees();

        model.addAttribute("reservationsNonValides", reservationsNonValides);
        model.addAttribute("reservationsValides", reservationsValides);
        model.addAttribute("reservationsTraitees", reservationsTraites);
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
            // Changer le statut vers "Validé" (supposons que l'ID 2 = Validé)
            Statut statutValide = statutService.findById(2).orElse(null);
            if (statutValide != null) {
                reservation.setStatut(statutValide);
                reservationService.save(reservation);
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statutValide.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutValide);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            refreshReservationsList(model);
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
            // Changer le statut vers "Refusé" (supposons que l'ID 3 = Refusé)
            Statut statutRefuse = statutService.findById(3).orElse(null);
            if (statutRefuse != null) {
                reservation.setStatut(statutRefuse);
                reservationService.save(reservation);
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statutRefuse.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutRefuse);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            refreshReservationsList(model);
            model.addAttribute("success", "Réservation refusée avec succès.");
        } else {
            model.addAttribute("error", "Réservation introuvable.");
        }
        
        // Rafraîchir la liste après refus
        refreshReservationsList(model);
        return "validation-reservations";
    }

    private void refreshReservationsList(Model model) {
        List<Reservation> reservationsNonValides = reservationService.findNonValide();
        List<Reservation> reservationsValides = reservationService.findValide();
        List<Reservation> reservationsTraitees = reservationService.findTraitees();

        // Filtrer les réservations traitées (statut != 2 et admin != null)
        
        // Préchargement des données pour éviter lazy loading
        for (Reservation res : reservationsNonValides) {
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) {
                res.getAdherant().getPersonne().getNom();
            }
            if (res.getExemplaire() != null) {
                res.getExemplaire().getNumero();
            }
        }
        for (Reservation res : reservationsValides) {
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) {
                res.getAdherant().getPersonne().getNom();
            }
            if (res.getExemplaire() != null) {
                res.getExemplaire().getNumero();
            }
        }
        for (Reservation res : reservationsTraitees) {
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) {
                res.getAdherant().getPersonne().getNom();
            }
            if (res.getExemplaire() != null) {
                res.getExemplaire().getNumero();
            }
        }
        model.addAttribute("reservationsNonValides", reservationsNonValides);
        model.addAttribute("reservationsValides", reservationsValides);
        model.addAttribute("reservationsTraitees", reservationsTraitees);
    }

    @PostMapping("/prendre-livre-reserve") 
    public String prendreLivreReserve(@RequestParam("idReservation") int idReservation,
                                        @RequestParam("typePret") int typePretId,
                                      @SessionAttribute("adminConnecte") Admin admin,
                                      Model model) {
        Reservation reservation = reservationService.findById(idReservation).orElse(null);
        Optional<TypePret> typePretOpt = typePretService.findById(typePretId);
        if (reservation != null && typePretOpt.isPresent()) {
            Pret pret = new Pret();
            pret.setAdherant(reservation.getAdherant());
            pret.setExemplaire(reservation.getExemplaire());
            pret.setAdmin(admin);
            Date dateDebut = new Date();
            pret.setDateDebut(dateDebut);
            double dureeJour = regleDureeService.getDureePourTypeAdherantAlaDate(
                reservation.getAdherant().getTypeAdherant(),
                dateDebut.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
            );
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebut);
            cal.add(Calendar.DAY_OF_MONTH, (int) dureeJour);
            pret.setDateFin(cal.getTime());
            pret.setTypePret(typePretOpt.get());
            pretService.save(pret);

            Statut statutPret = statutService.findById(4).orElse(null);
            if (statutPret != null) {
                reservation.setStatut(statutPret);
                reservationService.save(reservation);
                StatutReservation statutReservation = new StatutReservation();
                statutReservation.setId(new StatutReservationId(reservation.getIdReservation(), statutPret.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutPret);
                statutReservation.setDateStatut(new java.util.Date());
                statutReservationService.save(statutReservation);
            }
            model.addAttribute("success", "Livre réservé transformé en prêt avec succès.");
        } else {
            model.addAttribute("error", "Réservation ou type de prêt introuvable.");
        }
        refreshReservationsList(model);
        return "validation-reservations";
    }

    @PostMapping("/expire-reservation")
    public String expireReservation(@RequestParam("idReservation") int idReservation,
                                    @SessionAttribute("adminConnecte") Admin admin,
                                    Model model) {
        Reservation reservation = reservationService.findById(idReservation).orElse(null);
        if (reservation != null) {
            // Mettre à jour l'admin qui a expiré la réservation
            reservation.setAdmin(admin);
            Statut statutExpire = statutService.findById(5).orElse(null);
            if (statutExpire != null) {
                reservation.setStatut(statutExpire);
                reservationService.save(reservation);
                StatutReservation statutReservation = new StatutReservation();      
                statutReservation.setId(new StatutReservationId(idReservation, statutExpire.getIdStatut()));
                statutReservation.setReservation(reservation);
                statutReservation.setStatut(statutExpire);
                statutReservation.setDateStatut(new Date());
                statutReservationService.save(statutReservation);
            }
            model.addAttribute("success", "Réservation expirée avec succès.");
        }
        refreshReservationsList(model);
        return "validation-reservations";
    }

    @GetMapping("/prendre-livre-reserve")
    public String showPrendreLivreForm(@RequestParam("idReservation") int idReservation, Model model) {
        Reservation reservation = reservationService.findById(idReservation).orElse(null);
        List<TypePret> typesPret = typePretService.findAll();
        model.addAttribute("reservation", reservation);
        model.addAttribute("typesPret", typesPret);
        return "prendre-livre-reserve";
    }
}