package com.example.socialmeli.domains;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPromo extends Post{

    private Boolean hasPromo;
    private Double discount;

    public PostPromo(User user, Date date, Product detail, String category, Double price, Boolean hasPromo, Double discount) {
        super(user, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
