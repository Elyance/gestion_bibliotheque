package biblio.dev.service.fonctionnalite;

import biblio.dev.repository.fonctionnalite.JourFerierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class JourFerierService {
    @Autowired
    private JourFerierRepository jourFerierRepository;

    public boolean isJourFerier(Date date) {
        return jourFerierRepository.existsByDate(date);
    }
}