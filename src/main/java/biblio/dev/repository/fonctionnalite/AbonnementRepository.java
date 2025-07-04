package biblio.dev.repository.fonctionnalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.fonctionnalite.Abonnement;
import biblio.dev.entity.personne.Adherant;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findByAdherant(Adherant adherant);
}
