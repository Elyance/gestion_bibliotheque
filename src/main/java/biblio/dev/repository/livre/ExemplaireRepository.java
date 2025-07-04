package biblio.dev.repository.livre;

import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.livre.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    List<Exemplaire> findByLivre(Livre livre);
}
