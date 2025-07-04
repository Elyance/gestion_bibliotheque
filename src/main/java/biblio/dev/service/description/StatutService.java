package biblio.dev.service.description;

import biblio.dev.entity.description.Statut;
import biblio.dev.repository.description.StatutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatutService {
    @Autowired
    private StatutRepository statutRepository;

    public List<Statut> findAll() {
        return statutRepository.findAll();
    }

    public Optional<Statut> findById(Integer id) {
        return statutRepository.findById(id);
    }

    public Statut save(Statut statut) {
        return statutRepository.save(statut);
    }

    public void deleteById(Integer id) {
        statutRepository.deleteById(id);
    }
}
