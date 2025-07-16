package biblio.dev.repository.fonctionnalite;

import biblio.dev.entity.fonctionnalite.StatutDemande;
import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatutDemandeRepository extends JpaRepository<StatutDemande, Integer> {
    List<StatutDemande> findByDemandeProlongement(DemandeProlongement demande);
    StatutDemande findTopByDemandeProlongementOrderByDateStatutDesc(DemandeProlongement demande);
    List<StatutDemande> findByStatut_IdStatut(int idStatut);
}