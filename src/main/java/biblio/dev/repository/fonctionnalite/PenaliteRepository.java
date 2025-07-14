package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Penalite;
import biblio.dev.entity.personne.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {

    @Query("SELECT p FROM Penalite p WHERE p.pret.adherant = :adherant")
    List<Penalite> getPenaliteByAdherent(@Param("adherant") Adherant adherant);

    @Query("SELECT p FROM Penalite p WHERE p.pret.adherant = :adherant AND p.date <= :dateFin")
    List<Penalite> findPenalitesPossibles(
        @Param("adherant") Adherant adherant,
        @Param("dateFin") Date dateFin
    );

    // Récupère la dernière pénalité pour un adhérent
    @Query("SELECT p FROM Penalite p WHERE p.pret.adherant = :adherant ORDER BY p.date DESC LIMIT 1")
    Penalite getDernierePenalite(@Param("adherant") Adherant adherant);
}
