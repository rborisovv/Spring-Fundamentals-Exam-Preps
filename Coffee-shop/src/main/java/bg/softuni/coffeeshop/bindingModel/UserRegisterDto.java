package bg.softuni.coffeeshop.bindingModel;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRegisterDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    private String firstName;

    @NotBlank
    @Size(min = 5, max = 20)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;
}