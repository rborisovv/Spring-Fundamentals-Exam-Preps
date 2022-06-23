package bg.softuni.coffeeshop.model;

import bg.softuni.coffeeshop.enumeration.CategoryEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NonNull
    private CategoryEnum name;

    private Integer neededTime;
}