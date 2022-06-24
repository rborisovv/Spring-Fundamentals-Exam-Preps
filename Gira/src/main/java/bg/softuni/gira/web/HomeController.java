package bg.softuni.gira.web;

import bg.softuni.gira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final TaskService taskService;

    public HomeController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {

        model.addAttribute("tasks", taskService.findAll());
        return "home";
    }
}