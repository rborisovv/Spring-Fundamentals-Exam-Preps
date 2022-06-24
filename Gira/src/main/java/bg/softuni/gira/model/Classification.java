package bg.softuni.gira.model;

import bg.softuni.gira.enumeration.ClassificationEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Classification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @NonNull
    private ClassificationEnum classificationName;

    @Column
    private String description;
}