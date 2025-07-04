package biblio.dev.repository.personne;

import biblio.dev.entity.personne.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
    // Recherche une personne par mail et mot de passe
    Personne findByMailAndPassword(String mail, String password);
    
    @Query(value = "SELECT TIMESTAMPDIFF(YEAR, dateNaissance, CURDATE()) FROM Personne WHERE idPersonne = :id", nativeQuery = true)
    int getAgeById(@Param("id") int id);

}
