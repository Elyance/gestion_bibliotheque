package biblio.dev.repository.regle;

import biblio.dev.entity.regle.RegleNbLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.personne.TypeAdherant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Date;

@Repository
public interface RegleNbLivreRepository extends JpaRepository<RegleNbLivre, Integer> {
    
    // On récupère la règle la plus récente pour un type d’adhérant donné
    RegleNbLivre findTopByTypeAdherantOrderByDateDesc(TypeAdherant typeAdherant);

    // Nouvelle méthode : récupère la règle la plus récente à une date donnée
    @Query("SELECT r FROM RegleNbLivre r WHERE r.typeAdherant = :typeAdherant AND r.date <= :date ORDER BY r.date DESC")
    RegleNbLivre findTopByTypeAdherantAndDateBeforeOrderByDateDesc(@Param("typeAdherant") TypeAdherant typeAdherant,
                                                                  @Param("date") Date date);
}
