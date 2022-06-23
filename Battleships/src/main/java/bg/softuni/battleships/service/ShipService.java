package bg.softuni.battleships.service;

import bg.softuni.battleships.bindingModel.ShipBattleDto;
import bg.softuni.battleships.bindingModel.ShipDto;
import bg.softuni.battleships.dao.ShipRepository;
import bg.softuni.battleships.model.Category;
import bg.softuni.battleships.model.Ship;
import bg.softuni.battleships.model.User;
import bg.softuni.battleships.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserSession userSession;
    private final CategoryService categoryService;

    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper, UserService userService, UserSession userSession, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userSession = userSession;
        this.categoryService = categoryService;
    }

    public boolean createShip(ShipDto shipDto) {
        String shipName = shipDto.getName();

        Optional<Ship> optionalShip = shipRepository.findShipByName(shipName);

        if (optionalShip.isPresent()) {
            return false;
        }

        Ship shipEntity = modelMapper.map(shipDto, Ship.class);
        User user = userService.findById(userSession.getId());
        shipEntity.setUser(user);
        Category category = categoryService.findByName(shipDto.getCategory());
        shipEntity.setCategory(category);
        shipRepository.save(shipEntity);
        return true;
    }

    public List<Ship> findAllShips() {
        return shipRepository.findAll();
    }

    public void shipBattle(ShipBattleDto shipBattleDto) {
        Ship attacker = shipRepository.findShipByName(shipBattleDto.getAttackerName()).orElse(null);
        Ship defender = shipRepository.findShipByName(shipBattleDto.getDefenderName()).orElse(null);

        assert attacker != null;
        assert defender != null;
        if (attacker.getPower() >= defender.getHealth()) {
            shipRepository.delete(defender);
            return;
        }

        defender.setHealth(defender.getHealth() - attacker.getPower());
        shipRepository.save(defender);
    }
}