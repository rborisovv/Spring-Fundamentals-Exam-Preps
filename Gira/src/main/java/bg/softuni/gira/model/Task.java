package bg.softuni.gira.model;

import bg.softuni.gira.enumeration.ProgressEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private ProgressEnum progress;

    @Column
    private LocalDate dueDate;

    @ManyToOne
    private Classification classification;

    @ManyToOne
    private User user;
}