package bg.softuni.shoppinglist.bindingModel;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
}