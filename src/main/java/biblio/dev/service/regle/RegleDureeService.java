package biblio.dev.service.regle;

import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.entity.regle.RegleDuree;
import biblio.dev.repository.regle.RegleDureeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegleDureeService {

    @Autowired
    private RegleDureeRepository regleDureeRepository;

    public double getDureePourTypeAdherant(TypeAdherant typeAdherant) {
        List<RegleDuree> regles = regleDureeRepository.findByTypeOrderByDateDesc(typeAdherant);
        if (regles != null && !regles.isEmpty()) {
            return regles.get(0).getDuree(); // la plus récente
        }
        return 0; // valeur par défaut si aucune règle
    }

    // Pour gérer d'autres cas si besoin (ex: ajout, update, liste)
    public void save(RegleDuree regleDuree) {
        regleDureeRepository.save(regleDuree);
    }

    public List<RegleDuree> findAll() {
        return regleDureeRepository.findAll();
    }
}
