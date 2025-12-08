package de.gfn.web.customermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@RestController // Zum Aufgabu von APIs
@Controller
public class MainController {

    @Autowired // Der Server erzeugt automatisch ein Objekt und weist es zu
    private UserRepository repo;

    // http://localhost:8080/
    @GetMapping("/")
    public String start(Model ui) {
        // Über ui (Model) können Werte vom Controller an die Templates übergeben werden
        ui.addAttribute("title", "Startseite");
        return "standard"; // Name der HTML-Datei im resources/templates Ordner
    }

    // http://localhost:8080/form
    @GetMapping("/form")
    public String form(Model ui) {
        ui.addAttribute("title", "Dies und das");
        return "standard";
    }

    @GetMapping("/save")
    public String save(Model ui) {
        User user = new User("Peter", "Parker", LocalDate.now().minusYears(20));
        repo.save(user);
        return "redirect:/list"; // Umleitung auf die Liste
    }

    // http://localhost:8080/list
    @GetMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("title", "Liste mit Inhalten");

//        List<User> user = new ArrayList<>();
//        user.add(new User("Peter", "Parker", LocalDate.now().minusYears(20)));
//        user.add(new User("Buce", "Banner", LocalDate.now().minusYears(32).minusWeeks(20)));
//        user.add(new User("Steve", "Rogers", LocalDate.now().minusYears(75).plusWeeks(15)));

        List<User> user = repo.findAll();

        ui.addAttribute("user", user);

        return "standard";
    }
}
