package challenge1springboot.socialmeli.DTO;

import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    int id_post;
    String date;
    Product detail;
    int category;
    double price;
}
    /*
        "id_post" : 32,
        "date" : "01-05-2021”,
        "detail" :
                {
                “product_id” : 62,
                "productName" : "Headset RGB Inalámbrico",
                "type" : "Gamer",,
                "brand" : "Razer"
                "color" : "Green with RGB",
                "notes" : "Sin Batería"
                },
         "category" : "120",
         "price" : 2800.69,
    */
