package biblio.dev.repository.description;

import biblio.dev.entity.description.StatutReservation;
import biblio.dev.entity.description.StatutReservationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatutReservationRepository extends JpaRepository<StatutReservation, StatutReservationId> {
    
    /**
     * Trouve tous les statuts d'une réservation ordonnés par date décroissante
     * (le plus récent en premier)
     */
    List<StatutReservation> findByReservationIdReservationOrderByDateStatutDesc(int idReservation);
    
    /**
     * Trouve le statut le plus récent d'une réservation
     */
    @Query("SELECT sr FROM StatutReservation sr WHERE sr.reservation.idReservation = :idReservation ORDER BY sr.dateStatut DESC")
    List<StatutReservation> findLatestStatutByReservation(@Param("idReservation") int idReservation);
    
    /**
     * Trouve toutes les réservations ayant un statut spécifique
     */
    @Query("SELECT sr FROM StatutReservation sr WHERE sr.statut.nomStatut = :nomStatut ORDER BY sr.dateStatut DESC")
    List<StatutReservation> findByStatutNom(@Param("nomStatut") String nomStatut);
    
    /**
     * Trouve les statuts d'une réservation par ID de réservation
     */
    List<StatutReservation> findByReservationIdReservation(int idReservation);
}