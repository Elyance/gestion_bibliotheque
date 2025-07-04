package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.repository.fonctionnalite.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PretService {
    
    @Autowired
    private PretRepository pretRepository;

    // Méthodes CRUD de base
    public List<Pret> findAll() {
        return pretRepository.findAll();
    }

    public Optional<Pret> findById(Integer id) {
        return pretRepository.findById(id);
    }

    public Pret save(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deleteById(Integer id) {
        pretRepository.deleteById(id);
    }

    // Méthodes métier spécifiques
    public List<Pret> findByAdherant(Adherant adherant) {
        return pretRepository.findByAdherant(adherant);
    }

    public List<Pret> findByExemplaire(Exemplaire exemplaire) {
        return pretRepository.findByExemplaire(exemplaire);
    }

    public List<Pret> findPretsEnCours() {
        return pretRepository.findPretsEnCours(LocalDateTime.now());
    }

    public List<Pret> findPretsExpires() {
        return pretRepository.findPretsExpires(LocalDateTime.now());
    }

    public List<Pret> findPretsEnCoursByAdherant(Adherant adherant) {
        return pretRepository.findPretsEnCoursByAdherant(adherant, LocalDateTime.now());
    }

    public List<Pret> findByPeriode(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return pretRepository.findByPeriode(dateDebut, dateFin);
    }

    public boolean isExemplaireDisponible(Exemplaire exemplaire) {
        return !pretRepository.isExemplaireEmprunte(exemplaire, LocalDateTime.now());
    }

    // Méthode pour créer un nouveau prêt
    public Pret creerPret(Pret pret) {
        if (!isExemplaireDisponible(pret.getExemplaire())) {
            throw new IllegalStateException("Cet exemplaire est déjà emprunté");
        }
        return save(pret);
    }

    // Méthode pour prolonger un prêt
    public Pret prolongerPret(Integer idPret, int nombreJours) {
        Optional<Pret> optionalPret = findById(idPret);
        if (optionalPret.isPresent()) {
            Pret pret = optionalPret.get();
            pret.setDateFin(pret.getDateFin().plusDays(nombreJours));
            return save(pret);
        }
        throw new IllegalArgumentException("Prêt non trouvé avec l'ID: " + idPret);
    }

    // Méthode pour retourner un livre (mettre fin au prêt)
    public void terminerPret(Integer idPret) {
        Optional<Pret> optionalPret = findById(idPret);
        if (optionalPret.isPresent()) {
            Pret pret = optionalPret.get();
            pret.setDateFin(LocalDateTime.now());
            save(pret);
        } else {
            throw new IllegalArgumentException("Prêt non trouvé avec l'ID: " + idPret);
        }
    }

    // Méthode pour calculer le nombre de jours de retard
    public long calculerJoursRetard(Pret pret) {
        if (pret.getDateFin().isBefore(LocalDateTime.now())) {
            return java.time.Duration.between(pret.getDateFin(), LocalDateTime.now()).toDays();
        }
        return 0;
    }
}