package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.QuotaProlongement;
import biblio.dev.entity.personne.TypeAdherant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotaProlongementRepository extends JpaRepository<QuotaProlongement, Integer> {
    QuotaProlongement findByTypeAdherant(TypeAdherant typeAdherant);
}