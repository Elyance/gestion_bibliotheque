package biblio.dev.repository.personne;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.Personne;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    Adherant findByPersonne(Personne personne);
    Optional<Adherant> findByNumeroAdherant(String numeroAdherant);
}
