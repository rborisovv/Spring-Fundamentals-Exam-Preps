package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.bindingModel.OrderDto;
import bg.softuni.coffeeshop.service.CategoryService;
import bg.softuni.coffeeshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CategoryService categoryService;

    public OrderController(OrderService orderService, CategoryService categoryService) {
        this.orderService = orderService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("orderModel")
    public OrderDto orderDto() {
        return new OrderDto();
    }

    @GetMapping("/add")
    public String addOrderPage(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid OrderDto orderDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderModel", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderModel", bindingResult);
            return "redirect:/orders/add";
        }

        orderService.createOrder(orderDto);
        return "redirect:/home";
    }

    @GetMapping("/buy/{productId}")
    public String buyProduct(@PathVariable Long productId) {
        orderService.orderReady(productId);
        return "redirect:/home";
    }
}