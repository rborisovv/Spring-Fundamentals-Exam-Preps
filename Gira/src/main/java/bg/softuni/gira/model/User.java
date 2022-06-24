package bg.softuni.gira.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;
}