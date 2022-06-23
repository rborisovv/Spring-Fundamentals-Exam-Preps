package bg.softuni.battleships.web;

import bg.softuni.battleships.bindingModel.ShipDto;
import bg.softuni.battleships.dao.CategoryRepository;
import bg.softuni.battleships.service.ShipService;
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
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;
    private final CategoryRepository categoryRepository;

    public ShipController(ShipService shipService, CategoryRepository categoryRepository) {
        this.shipService = shipService;
        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("shipModel")
    public ShipDto shipDto() {
        return new ShipDto();
    }

    @GetMapping("/add")
    public String addShipPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "ship-add";
    }

    @PostMapping("/add")
    public String addShip(@Valid ShipDto shipDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipModel", shipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipModel", bindingResult);
            return "redirect:/ships/add";
        }

        boolean isCreated = shipService.createShip(shipDto);

        if (!isCreated) {
            redirectAttributes.addFlashAttribute("shipModel", shipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipModel", bindingResult);
            return "redirect:/ships/add";
        }
        return "redirect:/home";
    }
}