package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.service.CategoryService;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CategoryService categoryService, OrderService orderService, UserService userService) {
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("totalTime", orderService.findTotalOrderTime());
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("users", userService.findAllUsersOrderByOrdersCount());
        return "home";
    }
}