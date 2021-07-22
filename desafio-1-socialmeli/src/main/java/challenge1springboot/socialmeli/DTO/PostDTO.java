package challenge1springboot.socialmeli.DTO;

import challenge1springboot.socialmeli.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private int id_post;
    private String date;
    private Product detail;
    private int category;
    private double price;
}

