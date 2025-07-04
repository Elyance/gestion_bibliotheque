package biblio.dev.repository.livre;

import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.livre.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    List<Exemplaire> findByLivre(Livre livre);
    Optional<Exemplaire> findByNumero(String numero);
}
