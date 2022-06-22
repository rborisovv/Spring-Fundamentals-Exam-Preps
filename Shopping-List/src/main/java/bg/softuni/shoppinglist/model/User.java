package bg.softuni.shoppinglist.model;

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
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private String email;
}