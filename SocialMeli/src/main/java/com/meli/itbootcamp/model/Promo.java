package com.meli.itbootcamp.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Promo extends Post{

    private Boolean hasPromo;
    private Double discount;

    public Promo(Integer category, Product item, Double price, Boolean hasPromo, Double discount) {
        super(category, item, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
    public  Promo(Post post, Boolean hasPromo, Double discount){
        super(post.getCategory(), post.getItem(), post.getPrice());
        this.setId_post(post.getId_post());
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Post getPostfromPromo(){
        return this.getPost();
    }

}
