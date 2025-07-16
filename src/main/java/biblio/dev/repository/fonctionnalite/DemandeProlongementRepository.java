package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import biblio.dev.entity.personne.Adherant;
import java.util.List;

public interface DemandeProlongementRepository extends JpaRepository<DemandeProlongement, Integer> {
    // méthodes personnalisées si besoin
    List<DemandeProlongement> findByAdherant(Adherant adherant);
}
