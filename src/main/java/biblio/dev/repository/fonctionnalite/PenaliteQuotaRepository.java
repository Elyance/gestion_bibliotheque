package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.PenaliteQuota;
import biblio.dev.entity.personne.TypeAdherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaliteQuotaRepository extends JpaRepository<PenaliteQuota, Integer> {
    PenaliteQuota findByTypeAdherant(TypeAdherant typeAdherant);
}