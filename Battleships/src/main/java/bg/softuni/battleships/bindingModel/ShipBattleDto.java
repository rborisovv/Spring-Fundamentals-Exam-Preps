package bg.softuni.battleships.bindingModel;

import bg.softuni.battleships.model.Ship;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShipBattleDto {
    private String attackerName;

    private String defenderName;
}