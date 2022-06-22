package bg.softuni.battleships.model;

import bg.softuni.battleships.enums.CategoryEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @NonNull
    private CategoryEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;
}