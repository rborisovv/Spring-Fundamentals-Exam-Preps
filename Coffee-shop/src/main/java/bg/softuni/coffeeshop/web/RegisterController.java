package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.bindingModel.UserRegisterDto;
import bg.softuni.coffeeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerModel")
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDto userRegisterDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerModel", bindingResult);
            return "redirect:/users/register";
        }

        boolean success = userService.registerUser(userRegisterDto);

        if (!success) {
            redirectAttributes.addFlashAttribute("registerModel", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerModel", bindingResult);
            return "redirect:/users/register";
        }

        return "redirect:/users/login";
    }
}