package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.livre.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    
    // Trouver tous les prêts d'un adhérent
    List<Pret> findByAdherant(Adherant adherant);
    
    // Trouver tous les prêts d'un exemplaire
    List<Pret> findByExemplaire(Exemplaire exemplaire);
    
    // Trouver tous les prêts en cours (non expirés)
    @Query("SELECT p FROM Pret p WHERE p.dateFin > :now")
    List<Pret> findPretsEnCours(@Param("now") LocalDateTime now);
    
    // Trouver tous les prêts expirés
    @Query("SELECT p FROM Pret p WHERE p.dateFin < :now")
    List<Pret> findPretsExpires(@Param("now") LocalDateTime now);
    
    // Trouver les prêts d'un adhérent en cours
    @Query("SELECT p FROM Pret p WHERE p.adherant = :adherant AND p.dateFin > :now")
    List<Pret> findPretsEnCoursByAdherant(@Param("adherant") Adherant adherant, @Param("now") LocalDateTime now);
    
    // Trouver les prêts par période
    @Query("SELECT p FROM Pret p WHERE p.dateDebut >= :dateDebut AND p.dateDebut <= :dateFin")
    List<Pret> findByPeriode(@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);
    
    // Vérifier si un exemplaire est actuellement emprunté
    @Query("SELECT COUNT(p) > 0 FROM Pret p WHERE p.exemplaire = :exemplaire AND p.dateFin > :now")
    boolean isExemplaireEmprunte(@Param("exemplaire") Exemplaire exemplaire, @Param("now") LocalDateTime now);
}