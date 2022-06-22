package bg.softuni.shoppinglist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity{

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private LocalDateTime neededBefore;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}