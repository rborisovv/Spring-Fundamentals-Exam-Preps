package bg.softuni.gira.session;

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