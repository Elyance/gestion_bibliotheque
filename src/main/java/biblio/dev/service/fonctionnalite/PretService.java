
package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.repository.fonctionnalite.PretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

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
}

