package biblio.dev.repository.personne;

import biblio.dev.entity.personne.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    // Recherche une personne par mail et mot de passe
    Personne findByMailAndPassword(String mail, String password);
}
