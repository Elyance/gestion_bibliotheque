package biblio.dev.repository.fonctionnalite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.fonctionnalite.Retour;
import biblio.dev.entity.fonctionnalite.Pret;

import java.util.Optional;

@Repository
public interface RetourRepository extends JpaRepository<Retour, Integer> {

    Optional<Retour> findByPret(Pret pret); // Pour savoir si un prêt a été retourné
}
