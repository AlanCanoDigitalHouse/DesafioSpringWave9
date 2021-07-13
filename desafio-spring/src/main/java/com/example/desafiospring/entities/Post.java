package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {

    private Long id_post;
    private Long userId;
    private String date;
    private String category;
    private Double price;
    private boolean hasPromo;
    private Double discount;

}
