package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import biblio.dev.entity.fonctionnalite.TypePret;
import biblio.dev.entity.personne.Admin;
import org.springframework.ui.Model;
import java.sql.*;
import java.time.LocalDateTime;
import biblio.dev.entity.fonctionnalite.Reservation;
import biblio.dev.service.personne.AdherantService;
import biblio.dev.service.personne.PersonneService;
import biblio.dev.service.livre.*;
import biblio.dev.entity.livre.*;
import biblio.dev.service.regle.RegleNbLivreService;
import biblio.dev.service.regle.RegleDureeService;


@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private TypeAdherantLivreService typeAdherantLivreService;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private RegleNbLivreService regleNbLivreService;
    @Autowired
    private AdherantService adherantService;
    @Autowired
    private RegleDureeService regleDureeService;
    @Autowired
    private PenaliteService penaliteService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private biblio.dev.service.fonctionnalite.ReservationService reservationService;

    @Autowired
    private biblio.dev.service.fonctionnalite.JourFerierService jourFerierService;

    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    public Optional<Pret> findById(int id) {
        return pretRepository.findById(id);
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deleteById(int id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> findByAdherant(Adherant adherant) {
        return pretRepository.findByAdherant(adherant);
    }

    public List<Pret> findByExemplaire(Exemplaire exemplaire) {
        return pretRepository.findByExemplaire(exemplaire);
    }

    public int countPretsEnCoursParAdherant(Adherant adherant) {
        return pretRepository.countPretsEnCoursParAdherant(adherant);
    }

    // Retourne la liste des prêts retournés
    public List<Pret> findPretsRetournes() {
        return pretRepository.findAll().stream()
                .filter(pret -> pret.getRetour() != null)
                .toList();
    }

    // Retourne la liste des prêts non retournés
    public List<Pret> findPretsNonRetournes() {
        return pretRepository.findAll().stream()
                .filter(pret -> pret.getRetour() == null)
                .toList();
    }

    // Méthode utilitaire pour obtenir les réservations d'un exemplaire
    private List<Reservation> getReservationsByExemplaire(Exemplaire exemplaire) {
        List<Reservation> allReservations = reservationService.findAll();
        List<Reservation> result = new java.util.ArrayList<>();
        for (Reservation r : allReservations) {
            if (r.getExemplaire().getIdExemplaire() == exemplaire.getIdExemplaire()) {
                result.add(r);
            }
        }
        return result;
    }

    /**
     * Vérifie si un exemplaire est disponible (ni en prêt actif, ni réservé validé) à une date donnée
     */
    public boolean isExemplaireDisponible(Exemplaire exemplaire, LocalDateTime dateDebut) {    
        return livreService.isDispo(exemplaire, dateDebut.toLocalDate());   
    }

    /**
     * Vérifie toutes les règles métier pour un prêt (livre autorisé, âge, quota, abonnement, pénalité).
     * Retourne null si une règle échoue et ajoute le message d'erreur au modèle, sinon retourne un objet Pret prêt à être enregistré.
     */
    public Pret verifierEtConstruirePret(Adherant adherant, Exemplaire exemplaire, TypePret typePret, Admin admin, Model model, LocalDateTime dateDebut) {
        Livre livre = exemplaire.getLivre();
        Date dateDemande = Date.valueOf(dateDebut.toLocalDate());
        // 1. Âge
        int ageAdherant = personneService.getAgeById(adherant.getIdAdherant());
        if (ageAdherant < livre.getAgeLimite()) {
            String msg = "L'âge minimum requis pour ce livre est de " + livre.getAgeLimite() + " ans.";
            System.out.println("Règle 1 échouée : " + msg);
            model.addAttribute("error", msg);
            return null;
        }
        // 2. Quota
        int limite = regleNbLivreService.getLimitePourTypeAdherantAlaDate(adherant.getTypeAdherant(), dateDemande);
        int nbPretsActifs = this.countPretsEnCoursParAdherant(adherant);
        if (nbPretsActifs >= limite) {
            String msg = "Limite de " + limite + " prêt(s) atteinte pour cet adhérant.";
            System.out.println("Règle 2 échouée : " + msg);
            model.addAttribute("error", msg);
            return null;
        }
        // 3. Abonnement
        Date sqlDateDebut = Date.valueOf(dateDebut.toLocalDate());
        double dureeJour = regleDureeService.getDureePourTypeAdherantAlaDate(adherant.getTypeAdherant(), dateDebut.toLocalDate());
        java.time.LocalDateTime dateFin = dateDebut.plusDays((long) dureeJour);

        // Vérification jour férié pour la date de fin
        java.sql.Date sqlDateFin = java.sql.Date.valueOf(dateFin.toLocalDate());
        while (jourFerierService.isJourFerier(sqlDateFin)) {
            dateFin = dateFin.plusDays(1);
            sqlDateFin = java.sql.Date.valueOf(dateFin.toLocalDate());
        }

        if (!adherantService.isAbonnee(sqlDateDebut, sqlDateFin, adherant)) {
            String msg = "Cet adhérant n'est pas abonné durant toute la période de prêt.";
            System.out.println("Règle 3 échouée : " + msg);
            model.addAttribute("error", msg);
            return null;
        }
        // 4. Pénalité
        if (penaliteService.isPenaliseAlaDate(adherant, sqlDateDebut)) {
            String msg = "Cet adhérant est pénalisé pendant la période demandée.";
            System.out.println("Règle 4 échouée : " + msg);
            model.addAttribute("error", msg);
            return null;
        }
        // Si tout est OK, créer le prêt
        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setTypePret(typePret);
        pret.setExemplaire(exemplaire);
        pret.setAdmin(admin);
        pret.setDateDebut(Timestamp.valueOf(dateDebut));
        pret.setDateFin(Timestamp.valueOf(dateFin));
        return pret;
    }

    public Pret creerPretDepuisReservation(Reservation reservation, TypePret typePret, Admin admin, Model model) {
        // Utilise la date courante pour la transformation de réservation en prêt
        LocalDateTime dateDebut = LocalDateTime.now();
        return verifierEtConstruirePret(reservation.getAdherant(), reservation.getExemplaire(), typePret, admin, model, dateDebut);
    }
}

