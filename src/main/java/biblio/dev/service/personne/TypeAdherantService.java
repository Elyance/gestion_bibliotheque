package biblio.dev.service.personne;

import biblio.dev.entity.personne.TypeAdherant;
import biblio.dev.repository.personne.TypeAdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAdherantService {
    @Autowired
    private TypeAdherantRepository typeAdherantRepository;

    public List<TypeAdherant> findAll() {
        return typeAdherantRepository.findAll();
    }

    public Optional<TypeAdherant> findById(Integer id) {
        return typeAdherantRepository.findById(id);
    }

    public TypeAdherant save(TypeAdherant typeAdherant) {
        return typeAdherantRepository.save(typeAdherant);
    }

    public void deleteById(Integer id) {
        typeAdherantRepository.deleteById(id);
    }
}
