package de.gfn.web.customermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/groups") // Wird vor jedes Mapping dieses Controllers gesetzt
public class UserGroupController {

    @Autowired
    private UserGroupRepository repo;

    // http://localhost:8080/groups/list
    @GetMapping("/list")
    public String list(Model ui) {
//        List<UserGroup> groups = repo.findAll();
//        ui.addAttribute("groups", groups);
        ui.addAttribute("groups", repo.findAll());
        return "grouplist";
    }

    @GetMapping("/form")
    public String newForm(Model ui) {
        ui.addAttribute("group", new UserGroup());
        return "groupform";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam int id, Model ui) {
        Optional<UserGroup> opt = repo.findById(id);
        if(opt.isPresent()) {
            ui.addAttribute("group", opt.get());
        }
        else {
            ui.addAttribute("group", new UserGroup());
        }
        return "groupform";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        repo.deleteById(id);
        return "redirect:/groups/list";
    }

    @PostMapping("/save")
    public String save(UserGroup group) {
        repo.save(group);
        return "redirect:/groups/list";
    }
}
