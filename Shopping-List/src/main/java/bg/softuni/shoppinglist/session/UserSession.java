package bg.softuni.shoppinglist.session;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSession {
    private boolean isLoggedIn;

    private Long id;

    private String username;

    private String email;
}