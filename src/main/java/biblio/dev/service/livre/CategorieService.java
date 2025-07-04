package biblio.dev.service.livre;

import biblio.dev.entity.livre.Categorie;
import biblio.dev.repository.livre.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> findById(Integer id) {
        return categorieRepository.findById(id);
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteById(Integer id) {
        categorieRepository.deleteById(id);
    }
}
