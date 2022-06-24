package bg.softuni.gira.web;

import bg.softuni.gira.bindingModel.UserLoginDto;
import bg.softuni.gira.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("loginModel")
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("notFound")) {
            model.addAttribute("notFound", false);
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult);
            return "redirect:/users/login";
        }

        boolean isLoggedIn = userService.loginUser(userLoginDto);

        if (!isLoggedIn) {
            redirectAttributes.addFlashAttribute("loginModel", userLoginDto);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:/users/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/index";
    }
}