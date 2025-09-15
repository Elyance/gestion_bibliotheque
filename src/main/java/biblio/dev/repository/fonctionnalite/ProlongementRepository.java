package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Prolongement;
import biblio.dev.entity.personne.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByAdherant(Adherant adherant);
}