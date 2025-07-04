package biblio.dev.repository.fonctionnalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import biblio.dev.entity.fonctionnalite.TypePret;

@Repository
public interface TypePretRepository extends JpaRepository<TypePret, Integer> {
    // Tu peux ajouter des méthodes personnalisées ici si besoin
}
