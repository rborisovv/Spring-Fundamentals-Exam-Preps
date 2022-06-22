package bg.softuni.shoppinglist.bindingModel;

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
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;


    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    @NotBlank
    private String confirmPassword;
}