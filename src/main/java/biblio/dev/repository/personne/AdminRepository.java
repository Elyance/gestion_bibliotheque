package biblio.dev.repository.personne;

import biblio.dev.entity.personne.Admin;
import biblio.dev.entity.personne.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByPersonne(Personne personne);
}
