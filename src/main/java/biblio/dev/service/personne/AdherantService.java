package biblio.dev.service.personne;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.Personne;
import biblio.dev.repository.personne.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdherantService {
    @Autowired
    private AdherantRepository adherantRepository;

    public List<Adherant> findAll() {
        return adherantRepository.findAll();
    }

    public Optional<Adherant> findById(Integer id) {
        return adherantRepository.findById(id);
    }

    public Adherant save(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    public void deleteById(Integer id) {
        adherantRepository.deleteById(id);
    }

    public Adherant findByPersonne(Personne personne) {
        return adherantRepository.findByPersonne(personne);
    }

}
