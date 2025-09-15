package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.QuotaProlongement;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.fonctionnalite.QuotaProlongementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotaProlongementService {
    @Autowired
    private QuotaProlongementRepository quotaProlongementRepository;

    public int getQuotaMax(TypeAdherant typeAdherant) {
        QuotaProlongement quota = quotaProlongementRepository.findByTypeAdherant(typeAdherant);
        return quota != null ? quota.getNbProlongement() : 1; // valeur par défaut
    }
}