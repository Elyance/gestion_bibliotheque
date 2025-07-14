package biblio.dev.service.regle;

import biblio.dev.entity.regle.RegleNbLivre;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.regle.RegleNbLivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegleNbLivreService {

    @Autowired
    private RegleNbLivreRepository regleNbLivreRepository;

    public int getLimitePourTypeAdherant(TypeAdherant typeAdherant) {
        RegleNbLivre regle = regleNbLivreRepository.findTopByTypeAdherantOrderByDateDesc(typeAdherant);
        return (regle != null) ? regle.getNbLivre() : 0;
    }

    // Utilise la requête personnalisée du repository
    public int getLimitePourTypeAdherantAlaDate(TypeAdherant typeAdherant, java.sql.Date dateDebut) {
        RegleNbLivre regle = regleNbLivreRepository.findTopByTypeAdherantAndDateBeforeOrderByDateDesc(typeAdherant, dateDebut);
        return (regle != null) ? regle.getNbLivre() : 0;
    }
}
