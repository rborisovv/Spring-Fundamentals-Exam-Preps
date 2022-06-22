package bg.softuni.shoppinglist.web;

import bg.softuni.shoppinglist.bindingModel.AddProductDto;
import bg.softuni.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("productAddModel")
    public AddProductDto addProductDto() {
        return new AddProductDto();
    }

    @GetMapping("/add")
    public String addProductPage() {
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid AddProductDto addProductDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productModel", addProductDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel", bindingResult);
            return "redirect:/products/add";
        }

        productService.addProduct(addProductDto);
        return "redirect:/home";
    }

    @GetMapping("/buy/{productId}")
    public String buyProduct(@PathVariable Long productId) {
        productService.buyProduct(productId);
        return "redirect:/home";
    }

    @GetMapping("/buy-all")
    public String buyAllProducts() {
        productService.buyAll();
        return "redirect:/home";
    }
}