package biblio.dev.service.personne;

import biblio.dev.entity.personne.Admin;
import biblio.dev.repository.personne.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biblio.dev.entity.personne.Personne;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findById(Integer id) {
        return adminRepository.findById(id);
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

    public Admin findByPersonne(Personne personne) {
        return adminRepository.findByPersonne(personne);
    }
}
