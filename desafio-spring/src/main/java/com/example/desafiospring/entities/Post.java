package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long id_post;
    private Long userId;
    private String date;
    private String category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}
