package com.example.desafiospring.dtos;

import lombok.Data;

@Data
public class PostDto {

    private Long id_post;
    private String date;
    private ProductDto detail;
    private String category;
    private Double price;

}
