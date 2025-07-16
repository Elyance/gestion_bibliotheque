package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.JourFerier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface JourFerierRepository extends JpaRepository<JourFerier, Integer> {
    boolean existsByDate(Date date);
}