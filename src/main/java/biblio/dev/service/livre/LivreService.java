package biblio.dev.service.livre;

import biblio.dev.entity.livre.Livre;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.livre.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.service.fonctionnalite.PretService;
import biblio.dev.service.fonctionnalite.ReservationService;
import java.time.LocalDate;


@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private PretService pretService;
    @Autowired
    private ReservationService reservationService;

    public List<Livre> findAll() {
        return livreRepository.findAllWithCategories();
    }

    public Optional<Livre> findById(Integer id) {
        return livreRepository.findById(id);
    }

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteById(Integer id) {
        livreRepository.deleteById(id);
    }

    public boolean isAutorised(Livre livre, TypeAdherant typeAdherant) {
        return livreRepository.isLivreAutorisePourTypeAdherant(livre.getIdLivre(), typeAdherant.getIdTypeAdherant()) >= 0;
    }

    /**
     * Vérifie si un exemplaire est disponible (ni en prêt actif, ni réservé validé)
     */
    public boolean isDispo(Exemplaire exemplaire) {
        // Compatibilité ancienne signature : disponibilité à la date du jour
        return isDispo(exemplaire, java.time.LocalDate.now());
    }

    /**
     * Vérifie si un exemplaire est disponible à une date donnée (ni en prêt actif, ni réservé validé)
     */
    public boolean isDispo(Exemplaire exemplaire, LocalDate date) {
        List<Pret> pretsExemplaire = pretService.findByExemplaire(exemplaire);
        
        System.out.println("=== DEBUG isDispo ===");
        System.out.println("Exemplaire: " + exemplaire.getNumero());
        System.out.println("Date vérifiée: " + date);
        System.out.println("Nombre de prêts: " + pretsExemplaire.size());
        
        // Un exemplaire est disponible s'il n'a aucun prêt actif à la date donnée
        boolean exemplaireEnPret = pretsExemplaire.stream().anyMatch(p -> {
            LocalDate debut = toLocalDate(p.getDateDebut());
            
            System.out.println("  Prêt " + p.getIdPret() + " - Début: " + debut);
            System.out.println("  Retour: " + (p.getRetour() != null ? p.getRetour().getDateRetour() : "null"));
            
            if (debut == null || date.isBefore(debut)) {
                System.out.println("  -> Prêt pas encore commencé");
                return false; // Prêt pas encore commencé
            }
            
            if (p.getRetour() != null) {
                // Prêt retourné : actif seulement entre début et retour
                LocalDate dateRetour = toLocalDate(p.getRetour().getDateRetour());
                System.out.println("  -> Prêt retourné le: " + dateRetour);
                
                // CORRECTION: Un prêt retourné est actif si date >= début ET date <= retour
                boolean pretActif = !date.isBefore(debut) && !date.isAfter(dateRetour);
                System.out.println("  -> Prêt actif à cette date: " + pretActif);
                return pretActif;
            } else {
                // Prêt non retourné : actif depuis le début
                System.out.println("  -> Prêt non retourné, actif depuis le début");
                return true;
            }
        });

        System.out.println("Exemplaire en prêt: " + exemplaireEnPret);
        System.out.println("Exemplaire disponible: " + !exemplaireEnPret);
        return !exemplaireEnPret;
    }

    /**
     * Utilitaire pour convertir java.util.Date en java.time.LocalDate
     */
    public static LocalDate toLocalDate(java.util.Date date) {
        if (date == null) return null;
        if (date instanceof java.sql.Date) {
            return ((java.sql.Date) date).toLocalDate();
        } else {
            return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        }
    }
}
