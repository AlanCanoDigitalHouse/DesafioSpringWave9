package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponseDTO {
    private Integer id_post;
    private String date;
    private ProductResponseDTO detail;
    private Integer category;
    private Double price;
}
