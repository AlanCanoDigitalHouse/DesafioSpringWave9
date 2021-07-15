package desafiospringw9.demo.models;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

public class PostModel {

    private int userId;
    private int postId;
    private Date date;
    private int productId;
    private int category;
    private double price;

    public PostModel(int userId, int postId, Date date, int productId, int category, double price) {
        this.userId = userId;
        this.postId = postId;
        this.date = date;
        this.productId = productId;
        this.category = category;
        this.price = price;
    }
}
