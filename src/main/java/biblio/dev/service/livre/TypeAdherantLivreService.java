package biblio.dev.service.livre;

import biblio.dev.entity.livre.TypeAdherantLivre;
import biblio.dev.entity.livre.TypeAdherantLivreId;
import biblio.dev.repository.livre.TypeAdherantLivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import biblio.dev.entity.personne.Adherant;
import biblio.dev.entity.livre.Livre;
import java.util.Date;

@Service
public class TypeAdherantLivreService {
    @Autowired
    private TypeAdherantLivreRepository repository;

    public List<TypeAdherantLivre> findAll() {
        return repository.findAll();
    }

    public Optional<TypeAdherantLivre> findById(TypeAdherantLivreId id) {
        return repository.findById(id);
    }

    public TypeAdherantLivre save(TypeAdherantLivre entity) {
        return repository.save(entity);
    }

    public void deleteById(TypeAdherantLivreId id) {
        repository.deleteById(id);
    }

    /**
     * Vérifie si un livre est autorisé pour le type d'adhérant à une date donnée
     * @param adherant l'adhérant
     * @param idLivre l'identifiant du livre
     * @param date la date à vérifier
     * @return true si autorisé, false sinon
     */
    public boolean isLivreAutorisePourAdherant(Adherant adherant, Livre livre, Date date) {  
        Integer idTypeAdherant = adherant.getTypeAdherant().getIdTypeAdherant();
        Integer idLivre = livre.getIdLivre();
        List<TypeAdherantLivre> relations = repository.findAll();
        for (TypeAdherantLivre rel : relations) {
            if (rel.getTypeAdherant().getIdTypeAdherant().equals(idTypeAdherant)
                && rel.getLivre().getIdLivre() == idLivre
                && rel.getDate() != null
                && !date.before(rel.getDate())) {
                return true;
            }
        }
        return false;
    }
}
