package biblio.dev.repository.livre;

import biblio.dev.entity.livre.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    @Query("SELECT DISTINCT l FROM Livre l LEFT JOIN FETCH l.categories")
    List<Livre> findAllWithCategories();

    @Query(value = "SELECT COUNT(*) FROM TypeAdherant_Livre WHERE idLivre = :idLivre AND idTypeAdherant = :idTypeAdherant", nativeQuery = true)
    int isLivreAutorisePourTypeAdherant(@Param("idLivre") int idLivre, @Param("idTypeAdherant") int idTypeAdherant);
}
