package biblio.dev.controller.fonctionnalite;

import biblio.dev.entity.fonctionnalite.Pret;
import biblio.dev.entity.fonctionnalite.DemandeProlongement;
import biblio.dev.service.fonctionnalite.PretService;
import biblio.dev.service.fonctionnalite.DemandeProlongementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private DemandeProlongementService demandeProlongementService;

    // Afficher la liste des prêts
    @GetMapping("/prets")
    public String listePrets(Model model) {
        List<Pret> prets = pretService.findAll();
        model.addAttribute("prets", prets);
        model.addAttribute("titre", "Liste des prêts");
        return "list-prets";
    }

    // Afficher les prêts en cours
    //@GetMapping("/en-cours")
    //public String pretsEnCours(Model model) {
    //    List<Pret> pretsEnCours = pretService.findPretsEnCours();
    //    model.addAttribute("prets", pretsEnCours);
    //    model.addAttribute("titre", "Prêts en cours");
    //    return "listes-prets-en-cours";
    //}
//
    //// Afficher les prêts expirés
    //@GetMapping("/expires")
    //public String pretsExpires(Model model) {
    //    List<Pret> pretsExpires = pretService.findPretsExpires();
    //    model.addAttribute("prets", pretsExpires);
    //    model.addAttribute("titre", "Prêts expirés");
    //    return "prets/liste";
    //}
//
    //// Afficher les détails d'un prêt
    //@GetMapping("/prets/{id}")
    //public String detailsPret(@PathVariable Integer id, Model model) {
    //    Optional<Pret> pret = pretService.findById(id);
    //    if (pret.isPresent()) {
    //        model.addAttribute("pret", pret.get());
    //        long joursRetard = pretService.calculerJoursRetard(pret.get());
    //        model.addAttribute("joursRetard", joursRetard);
    //        return "prets/details";
    //    }
    //    return "redirect:/prets";
    //}
}