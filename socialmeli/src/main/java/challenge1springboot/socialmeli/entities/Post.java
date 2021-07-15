package challenge1springboot.socialmeli.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int id_post;
    private int userId;
    private String date;
    private Product detail;
    private int category;
    private double price;
}
