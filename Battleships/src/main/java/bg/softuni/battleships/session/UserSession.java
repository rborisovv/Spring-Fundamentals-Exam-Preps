package bg.softuni.battleships.session;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSession {
    private Long id;

    private String username;

    private String email;
}