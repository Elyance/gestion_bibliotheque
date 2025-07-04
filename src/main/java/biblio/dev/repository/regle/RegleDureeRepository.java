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
}
