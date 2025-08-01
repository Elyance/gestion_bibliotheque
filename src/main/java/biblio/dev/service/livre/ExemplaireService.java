package biblio.dev.service.livre;

import biblio.dev.entity.livre.Exemplaire;
import biblio.dev.entity.livre.Livre;
import biblio.dev.repository.livre.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> findAll() {
        return exemplaireRepository.findAll();
    }

    public Optional<Exemplaire> findById(Integer id) {
        return exemplaireRepository.findById(id);
    }

    public Exemplaire save(Exemplaire exemplaire) {
        return exemplaireRepository.save(exemplaire);
    }

    public void deleteById(Integer id) {
        exemplaireRepository.deleteById(id);
    }

    public List<Exemplaire> getExemplairesByLivre(Livre livre) {
        return exemplaireRepository.findByLivre(livre);
    }

    public Optional<Exemplaire> getByNumero(String numero) {
        return exemplaireRepository.findByNumero(numero);
    }
}
