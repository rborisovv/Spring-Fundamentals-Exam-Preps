package bg.softuni.gira.bindingModel;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginDto {
    @NotBlank(message = "Email cant be empty!")
    @Email(message = "Please enter a valid email address")
    private String email;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}