package biblio.dev.repository.regle;

import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.entity.regle.RegleDuree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegleDureeRepository extends JpaRepository<RegleDuree, Integer> {

    @Query("SELECT r FROM RegleDuree r WHERE r.typeAdherant = :typeAdherant ORDER BY r.date DESC")
    List<RegleDuree> findByTypeOrderByDateDesc(@Param("typeAdherant") TypeAdherant typeAdherant);

    // Nouvelle requête : règle la plus récente à une date donnée
    @Query("SELECT r FROM RegleDuree r WHERE r.typeAdherant = :typeAdherant AND r.date <= :date ORDER BY r.date DESC")
    RegleDuree findTopByTypeAdherantAndDateBeforeOrderByDateDesc(@Param("typeAdherant") TypeAdherant typeAdherant,
                                                                @Param("date") java.sql.Date date);
}
