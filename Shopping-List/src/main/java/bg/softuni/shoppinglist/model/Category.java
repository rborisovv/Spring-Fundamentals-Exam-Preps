package bg.softuni.shoppinglist.model;

import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @NonNull
    private CategoryEnum name;

    @Column
    private String description;
}