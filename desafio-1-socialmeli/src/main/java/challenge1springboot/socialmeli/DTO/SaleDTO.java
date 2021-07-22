package challenge1springboot.socialmeli.DTO;

import challenge1springboot.socialmeli.entities.Post;
import challenge1springboot.socialmeli.entities.Product;
import challenge1springboot.socialmeli.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private int id_post;
    private String date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public SaleDTO(Post post, Sale sale) {
        this.id_post = post.getId_post();
        this.date = post.getDate();
        this.detail = post.getDetail();
        this.category = post.getCategory();
        this.hasPromo = sale.isInSale();
        this.discount = sale.getDiscount();
    }
}
