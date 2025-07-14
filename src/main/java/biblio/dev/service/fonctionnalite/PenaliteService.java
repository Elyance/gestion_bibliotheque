package biblio.dev.service.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.fonctionnalite.Penalite;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.PenaliteRepository;
import java.util.*;

@Service
public class PenaliteService {

    @Autowired
    PenaliteRepository penaliteRepository;
    /**
     * 
     * 
     * Récupère la date de fin de la dernière pénalité pour un adhérent
     */
    public Penalite getDernierePenalite(Adherant adherant) {
        return penaliteRepository.getDernierePenalite(adherant);
    }
    /**
     * Crée une pénalité si le retour est en retard
     */
    public void penaliserSiRetard(Pret pret, Date dateRetour) {
        if (pret.getDateFin() != null) {
            Date dateFin = pret.getDateFin();
            if (dateRetour.after(dateFin)) {
                long diffMillis = dateRetour.getTime() - dateFin.getTime();
                int nbJoursRetard = (int) (diffMillis / (1000 * 60 * 60 * 24));
                if (nbJoursRetard > 0) {
                    Penalite dernierePenalite = getDernierePenalite(pret.getAdherant());
                    Date datePenalite;
                    if (dernierePenalite != null) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dernierePenalite.getDate());
                        cal.add(Calendar.DAY_OF_MONTH, dernierePenalite.getNbJourPenalite());
                        Date dateFinDernierePenalite = cal.getTime();
                        if (dateRetour.compareTo(dateFinDernierePenalite) <= 0) {
                            datePenalite = dateFinDernierePenalite;
                        } else {
                            datePenalite = dateRetour;
                        }
                    } else {
                        datePenalite = dateRetour;
                    }
                    Penalite penalite = new Penalite();
                    penalite.setPret(pret);
                    penalite.setDate(datePenalite);
                    penalite.setNbJourPenalite(nbJoursRetard);
                    penaliteRepository.save(penalite);
                }
            }
        }
    }
    

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
