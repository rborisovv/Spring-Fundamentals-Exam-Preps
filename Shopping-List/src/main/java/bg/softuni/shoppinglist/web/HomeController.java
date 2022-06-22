package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("totalPrice", this.productService.getTotalProductsPrice());
        model.addAttribute("foods", productService.findProductsByCategory(CategoryEnum.FOOD));
        model.addAttribute("drinks", productService.findProductsByCategory(CategoryEnum.DRINK));
        model.addAttribute("households", productService.findProductsByCategory(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", productService.findProductsByCategory(CategoryEnum.OTHER));
        return "/home";
    }
}