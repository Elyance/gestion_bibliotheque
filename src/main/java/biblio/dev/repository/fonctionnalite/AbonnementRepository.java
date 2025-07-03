package biblio.dev.repository.fonctionnalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.fonctionnalite.Abonnement;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    // Méthodes spécifiques pour l'abonnement si nécessaire    
}
