package challenge1springboot.socialmeli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    int product_id;
    String productName;
    String type;
    String brand;
    String color;
    String notes;
}
