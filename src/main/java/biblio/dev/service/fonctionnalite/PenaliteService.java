package biblio.dev.service.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.fonctionnalite.Penalite;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.PenaliteRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PenaliteService {
    @Autowired
    PenaliteRepository penaliteRepository;

    public List<Penalite> findPenaliteActiveParPeriode(Adherant adherant, Date dateDebut, Date dateFin) {
    List<Penalite> possibles = penaliteRepository.findPenalitesPossibles(adherant, dateFin);

    return possibles.stream()
        .filter(p -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(p.getDate());
            cal.add(Calendar.DAY_OF_MONTH, p.getNbJourPenalite());
            Date dateFinPenalite = cal.getTime();
            return dateFinPenalite.compareTo(dateDebut) >= 0;
        })
        .collect(Collectors.toList());
}

}
