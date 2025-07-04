package biblio.dev.repository.description;

import biblio.dev.entity.description.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatutRepository extends JpaRepository<Statut, Integer> {
    // Ajoutez ici des méthodes personnalisées si besoin
}
