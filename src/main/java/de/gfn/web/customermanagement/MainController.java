package de.gfn.web.customermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController // Zum Aufgabu von APIs
@Controller
public class MainController {

    @Autowired // Der Server erzeugt automatisch ein Objekt und weist es zu
    private UserRepository repo;

    @Autowired
    private UserGroupRepository groupRepo;

    // http://localhost:8080/
    @GetMapping("/")
    public String start(Model ui) {
        // Über ui (Model) können Werte vom Controller an die Templates übergeben werden
        ui.addAttribute("title", "Startseite");
        return "standard"; // Name der HTML-Datei im resources/templates Ordner
    }

    // http://localhost:8080/form
    @GetMapping("/form")
    public String newForm(Model ui) {
        ui.addAttribute("title", "Neuer User");
        ui.addAttribute("user", new User());
        ui.addAttribute("groups", groupRepo.findAll());
        return "form";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam int id, Model ui) {
        ui.addAttribute("title", "User bearbeiten");
        Optional<User> opt = repo.findById(id);
        if(opt.isPresent()) {
            ui.addAttribute("user", opt.get());
        }
        else {
            // TODO: Meldung, dass kein passender User gefunden wurde
            ui.addAttribute("user", new User());
        }
        return "form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, Model ui) {
        repo.deleteById(id); // Wenn vorhanden, wird gelöscht
        return "redirect:/list";
    }

    @PostMapping("/save")
    public String save(int id, String firstname, String lastname, LocalDate birthdate,  Model ui) {
        // TODO: Daten validieren
        User user = new User(firstname, lastname, birthdate);
        user.setId(id);
        repo.save(user);
        return "redirect:/list"; // Umleitung auf die Liste
    }

//    @PostMapping("/save")
//    public String save(User user,  Model ui) {
//        // TODO: Daten validieren
//        repo.save(user);
//        return "redirect:/list"; // Umleitung auf die Liste
//    }

    // http://localhost:8080/list
    @GetMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("title", "Userliste");
        List<User> user = repo.findAll();
        ui.addAttribute("user", user);
        return "list";
    }
}
