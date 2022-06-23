package bg.softuni.coffeeshop.bindingModel;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent
    private LocalDateTime orderTime;

    @NotBlank
    private String category;

    @NotBlank
    @Size(min = 5)
    private String description;
}