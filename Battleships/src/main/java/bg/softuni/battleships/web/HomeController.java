package bg.softuni.battleships.web;

import bg.softuni.battleships.bindingModel.ShipBattleDto;
import bg.softuni.battleships.model.Ship;
import bg.softuni.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ShipService shipService;

    public HomeController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("ships", shipService.findAllShips());
        model.addAttribute("shipModel", new ShipBattleDto());
        model.addAttribute("orderedShips", shipService.findAllShips()
                .stream()
                .sorted(Comparator.comparing(Ship::getName)
                        .thenComparing(Ship::getHealth)
                        .thenComparing(Ship::getPower))
                .collect(Collectors.toList()));
        return "home";
    }

    @PostMapping("/home/battle")
    public String shipsBattle(ShipBattleDto shipBattleDto) {

        if (shipBattleDto.getAttackerName().equals(shipBattleDto.getDefenderName())) {
            return "redirect:/home";
        }

        shipService.shipBattle(shipBattleDto);
        return "redirect:/home";
    }
}