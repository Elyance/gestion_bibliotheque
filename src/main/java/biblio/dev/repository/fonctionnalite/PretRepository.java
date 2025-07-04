package biblio.dev.repository.fonctionnalite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.personne.Adherant;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {

    List<Pret> findByAdherant(Adherant adherant);

    List<Pret> findByExemplaire(Exemplaire exemplaire);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherant.idAdherant = :idAdherant AND p.idPret NOT IN (SELECT r.pret.idPret FROM Retour r)")
    int countPretsNonRendus(@Param("idAdherant") int idAdherant);

    @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherant = :adherant AND p.idPret NOT IN (SELECT r.pret.idPret FROM Retour r)")
    int countPretsEnCoursParAdherant(@Param("adherant") Adherant adherant);


    // @Query("SELECT COUNT(p) > 0 FROM Pret p WHERE p.exemplaire.idExemplaire = :idExemplaire AND p.idPret NOT IN (SELECT r.pret.idPret FROM Retour r)")
    // boolean existsPretActifByExemplaire(@Param("idExemplaire") int idExemplaire);
}

