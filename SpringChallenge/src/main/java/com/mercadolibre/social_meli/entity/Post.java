package com.mercadolibre.social_meli.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Integer id_post;
    private Integer userId;
    private String date;
    private Product detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}
