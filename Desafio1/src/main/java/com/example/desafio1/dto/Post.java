package com.example.desafio1.dto;

import lombok.Data;

@Data
public class Post {
    private Integer postId;
    private Integer userId;
    private String date;
    private Product detail;
    private Integer category;
    private Double price;
}
