package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.entity.fonctionnalite.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeProlongementRepository extends JpaRepository<DemandeProlongement, Integer> {
    List<DemandeProlongement> findByPret(Pret pret);
}