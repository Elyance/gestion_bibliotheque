package biblio.dev.service.livre;

import biblio.dev.entity.livre.Livre;
import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.livre.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> findAll() {
        return livreRepository.findAllWithCategories();
    }

    public Optional<Livre> findById(Integer id) {
        return livreRepository.findById(id);
    }

    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteById(Integer id) {
        livreRepository.deleteById(id);
    }

    public boolean isAutorised(Livre livre,TypeAdherant typeAdherant) {
        return livreRepository.isLivreAutorisePourTypeAdherant(livre.getIdLivre(),typeAdherant.getIdTypeAdherant()) >= 0;
    }
}
