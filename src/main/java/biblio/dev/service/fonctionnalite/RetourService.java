package biblio.dev.service.fonctionnalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.fonctionnalite.Retour;
import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.repository.fonctionnalite.RetourRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class RetourService {

    @Autowired
    private RetourRepository retourRepository;

    public Retour save(Retour retour) {
        return retourRepository.save(retour);
    }

    public Optional<Retour> findByPret(Pret pret) {
        return retourRepository.findByPret(pret);
    }

    public boolean isPretRetourne(Pret pret) {
        return retourRepository.findByPret(pret).isPresent();
    }

    public void enregistrerRetour(Pret pret) {
        Retour retour = new Retour();
        retour.setPret(pret);
        retour.setDateRetour(new Date());
        retourRepository.save(retour);
    }
}
