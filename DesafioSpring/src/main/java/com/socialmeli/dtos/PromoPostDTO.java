package com.socialmeli.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PromoPostDTO extends PostDTO {

    private Boolean hasPromo;
    private Double discount;

    public PromoPostDTO(Integer userId, Integer postId, Date date, ProductDTO detail, Integer category, Double price, Boolean hasPromo, Double discount) {
        super(userId, postId, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PromoPostDTO(Integer userId, Integer postId, Date date, ProductDTO detail, Integer category, Double price) {
        super(userId, postId, date, detail, category, price);
    }
}
