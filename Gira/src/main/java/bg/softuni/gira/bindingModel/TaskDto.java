package bg.softuni.gira.bindingModel;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TaskDto {

    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDate dueDate;

    @NotBlank(message = "Classification cannot be null!")
    private String classification;
}