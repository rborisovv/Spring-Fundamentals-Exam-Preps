package bg.softuni.coffeeshop.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private LocalDateTime orderTime;

    @ManyToOne
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private User employee;
}