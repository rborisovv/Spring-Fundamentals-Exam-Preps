package bg.softuni.gira.web;

import bg.softuni.gira.bindingModel.TaskDto;
import bg.softuni.gira.service.ClassificationService;
import bg.softuni.gira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ClassificationService classificationService;

    public TaskController(TaskService taskService, ClassificationService classificationService) {
        this.taskService = taskService;
        this.classificationService = classificationService;
    }

    @ModelAttribute("taskModel")
    public TaskDto taskDto() {
        return new TaskDto();
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        if (!model.containsAttribute("classification")) {
            model.addAttribute("classifications", classificationService.findAll());
        }
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@Valid TaskDto taskDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskModel", taskDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskModel", bindingResult);
            return "redirect:/tasks/add";
        }

        boolean taskIsAdded = taskService.addTask(taskDto);

        if (!taskIsAdded) {
            redirectAttributes.addFlashAttribute("taskModel", taskDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskModel", bindingResult);
            return "redirect:/tasks/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/update/{taskId}")
    public String updateTask(@PathVariable Long taskId) {
        taskService.updateTask(taskId);

        return "redirect:/home";
    }
}