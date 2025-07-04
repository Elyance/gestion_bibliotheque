package biblio.dev.controller.affichage;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.description.StatutReservation;
import biblio.dev.service.fonctionnalite.ReservationService;
import biblio.dev.service.description.StatutReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ReservationListController {
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private StatutReservationService statutReservationService;

    @GetMapping("/liste-reservations")
    public String showAllReservations(@RequestParam(value = "statut", required = false) String statutFilter,
                                     @RequestParam(value = "adherant", required = false) String adherantFilter,
                                     Model model) {
        
        // Récupérer toutes les réservations
        List<Reservation> reservations = reservationService.findAll();
        
        // Filtrer par statut si spécifié
        if (statutFilter != null && !statutFilter.isEmpty() && !statutFilter.equals("tous")) {
            reservations = reservations.stream()
                .filter(res -> hasStatut(res, statutFilter))
                .collect(Collectors.toList());
        }
        
        // Filtrer par adhérant si spécifié
        if (adherantFilter != null && !adherantFilter.isEmpty()) {
            reservations = reservations.stream()
                .filter(res -> res.getAdherant() != null && 
                              res.getAdherant().getPersonne() != null &&
                              res.getAdherant().getPersonne().getNom().toLowerCase()
                                  .contains(adherantFilter.toLowerCase()))
                .collect(Collectors.toList());
        }
        
        // Forcer le chargement lazy des propriétés nécessaires
        for (Reservation res : reservations) {
            // Charger les données de l'adhérant
            if (res.getAdherant() != null && res.getAdherant().getPersonne() != null) {
                res.getAdherant().getPersonne().getNom();
            }
            
            // Charger les données de l'exemplaire et du livre
            if (res.getExemplaire() != null) {
                res.getExemplaire().getNumero();
                if (res.getExemplaire().getLivre() != null) {
                    res.getExemplaire().getLivre().getTitre();
                    res.getExemplaire().getLivre().getAuteur();
                }
            }
            
            // Charger les données de l'admin
            if (res.getAdmin() != null && res.getAdmin().getPersonne() != null) {
                res.getAdmin().getPersonne().getNom();
            }
        }
        
        // Récupérer les statuts actuels pour chaque réservation
        Map<Integer, StatutReservation> statutsActuels = reservations.stream()
            .collect(Collectors.toMap(
                Reservation::getIdReservation,
                this::getStatutActuel
            ));
        
        model.addAttribute("reservations", reservations);
        model.addAttribute("statutsActuels", statutsActuels);
        model.addAttribute("statutFilter", statutFilter);
        model.addAttribute("adherantFilter", adherantFilter);
        
        return "liste-reservations";
    }
    
    private boolean hasStatut(Reservation reservation, String statutNom) {
        StatutReservation statutActuel = getStatutActuel(reservation);
        return statutActuel != null && 
               statutActuel.getStatut() != null && 
               statutActuel.getStatut().getNomStatut().equalsIgnoreCase(statutNom);
    }
    
    private StatutReservation getStatutActuel(Reservation reservation) {
        // Récupérer le statut le plus récent pour cette réservation
        return statutReservationService.findLatestByReservation(reservation.getIdReservation());
    }
}