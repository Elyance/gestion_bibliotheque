package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.StatutDemande;
import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatutDemandeRepository extends JpaRepository<StatutDemande, Integer> {
    
    // Méthode de base
    List<StatutDemande> findByDemandeProlongement(DemandeProlongement demande);
    
    // Tri par timestamp DESC puis par ID DESC (le plus récent en premier)
    @Query("SELECT s FROM StatutDemande s WHERE s.demandeProlongement.idDemande = :idDemande ORDER BY s.dateStatut DESC, s.idStatutDemande DESC")
    List<StatutDemande> findByDemandeIdOrderByDateAndIdDesc(@Param("idDemande") int idDemande);
    
    // Récupérer directement le statut le plus récent
    @Query("SELECT s FROM StatutDemande s WHERE s.demandeProlongement.idDemande = :idDemande ORDER BY s.dateStatut DESC, s.idStatutDemande DESC LIMIT 1")
    Optional<StatutDemande> findLatestByDemandeId(@Param("idDemande") int idDemande);
    
    // Recherche par statut
    List<StatutDemande> findByStatut_IdStatut(int idStatut);
    
    // Recherche par demande et statut
    @Query("SELECT s FROM StatutDemande s WHERE s.demandeProlongement = :demande AND s.statut.idStatut = :idStatut ORDER BY s.dateStatut DESC")
    List<StatutDemande> findByDemandeAndStatut(@Param("demande") DemandeProlongement demande, @Param("idStatut") int idStatut);
    
    // Vérifier si une demande a un statut spécifique
    @Query("SELECT COUNT(s) > 0 FROM StatutDemande s WHERE s.demandeProlongement.idDemande = :idDemande AND s.statut.idStatut = :idStatut")
    boolean existsByDemandeIdAndStatutId(@Param("idDemande") int idDemande, @Param("idStatut") int idStatut);
    
    // Récupérer le dernier statut d'une demande avec jointure
    @Query("SELECT s FROM StatutDemande s JOIN FETCH s.statut WHERE s.demandeProlongement = :demande ORDER BY s.dateStatut DESC, s.idStatutDemande DESC")
    List<StatutDemande> findByDemandeWithStatutOrderByDateDesc(@Param("demande") DemandeProlongement demande);
}