package bg.softuni.battleships.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    @Column(unique = true)
    public String username;

    @Column
    private String fullName;

    @Column
    private String password;

    @Column(unique = true)
    private String email;
}