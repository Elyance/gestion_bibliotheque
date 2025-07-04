package biblio.dev.service.personne;

import biblio.dev.entity.personne.Personne;
import biblio.dev.repository.personne.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Optional<Personne> findById(Integer id) {
        return personneRepository.findById(id);
    }

    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deleteById(Integer id) {
        personneRepository.deleteById(id);
    }

    public Personne login(String mail, String password) {
        return personneRepository.findByMailAndPassword(mail, password);
    }

    public int getAgeById(int id) {
        return personneRepository.getAgeById(id);
    }

}
