package com.example.desafiospring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    private Integer postId;
    private Integer userId;
    private String date;
    private Integer productId;
    private Integer category;
    private Double price;
}
