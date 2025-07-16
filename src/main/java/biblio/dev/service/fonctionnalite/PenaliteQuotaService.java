package biblio.dev.service.fonctionnalite;

import biblio.dev.entity.fonctionnalite.PenaliteQuota;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.fonctionnalite.PenaliteQuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaliteQuotaService {
    @Autowired
    private PenaliteQuotaRepository penaliteQuotaRepository;

    public int getNbJourPenalite(TypeAdherant typeAdherant) {
        PenaliteQuota quota = penaliteQuotaRepository.findByTypeAdherant(typeAdherant);
        return quota != null ? quota.getNbJourPenalite() : 0;
    }
}