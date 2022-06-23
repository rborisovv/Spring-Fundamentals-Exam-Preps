package bg.softuni.battleships.bindingModel;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShipDto {

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Positive
    private Double power;

    @Positive
    private Double Health;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate created;

    private String category;
}