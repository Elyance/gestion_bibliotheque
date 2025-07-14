package biblio.dev.service.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.fonctionnalite.Penalite;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.PenaliteRepository;
import java.util.*;

@Service
public class PenaliteService {
    @Autowired
    PenaliteRepository penaliteRepository;

    /**
     * Vérifie si l'adhérant est pénalisé à la date du prêt
     */
    public boolean isPenaliseAlaDate(Adherant adherant, Date datePret) {
        List<Penalite> penalites = penaliteRepository.findPenalitesPossibles(adherant, datePret);
        for (Penalite p : penalites) {
            Date dateDebutPenalite = p.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebutPenalite);
            cal.add(Calendar.DAY_OF_MONTH, p.getNbJourPenalite());
            Date dateFinPenalite = cal.getTime();
            // Si la date du prêt est entre la date de début et la date de fin de pénalité
            if (!datePret.before(dateDebutPenalite) && !datePret.after(dateFinPenalite)) {
                return true;
            }
        }
        return false;
    }

}
