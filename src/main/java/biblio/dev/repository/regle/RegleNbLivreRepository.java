package biblio.dev.repository.regle;

import biblio.dev.entity.regle.RegleNbLivre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biblio.dev.entity.personne.TypeAdherant;

@Repository
public interface RegleNbLivreRepository extends JpaRepository<RegleNbLivre, Integer> {
    
    // On récupère la règle la plus récente pour un type d’adhérant donné
    RegleNbLivre findTopByTypeAdherantOrderByDateDesc(TypeAdherant typeAdherant);
}
