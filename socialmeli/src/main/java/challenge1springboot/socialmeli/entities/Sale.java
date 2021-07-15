package challenge1springboot.socialmeli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private int userId;
    private int id_post;
    private int product_id;
    private boolean inSale;
    private double discount;
}
