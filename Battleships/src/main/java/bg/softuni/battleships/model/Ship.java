package bg.softuni.battleships.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ship extends BaseEntity {
    @Column(unique = true)
    private String name;

    @Column
    private Double health;

    @Column
    private Double power;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}