package biblio.dev.repository.livre;

import biblio.dev.entity.livre.TypeAdherantLivre;
import biblio.dev.entity.livre.TypeAdherantLivreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAdherantLivreRepository extends JpaRepository<TypeAdherantLivre, TypeAdherantLivreId> {
    // Ajoute ici des méthodes personnalisées si besoin
}
