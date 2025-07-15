package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(int id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteById(int id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findNonValide() {
        return reservationRepository.findByAdminIsNull();
    }

    // La logique de filtrage par statut doit être faite côté contrôleur
    public List<Reservation> findValide() {
        // statut == 2 et admin != null
        List<Reservation> all = reservationRepository.findByAdminIsNotNull();
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : all) {
            if (r.getStatut() != null && r.getStatut().getIdStatut() == 2) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Reservation> findTraitees() {
        // statut != 2 et admin != null
        List<Reservation> all = reservationRepository.findByAdminIsNotNull();
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : all) {
            if (r.getStatut() != null && r.getStatut().getIdStatut() != 2) {
                result.add(r);
            }
        }
        return result;
    }

    public List<Reservation> findByAdherant(Adherant adherant) {
        return reservationRepository.findByAdherant(adherant);
    }

}