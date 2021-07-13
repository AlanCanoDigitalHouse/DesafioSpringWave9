package com.example.desafiospring.dtos.response;

import com.example.desafiospring.utils.Factory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostResponseDto {
    @JsonIgnore
    private Integer userId;
    private Integer id_post;
    private String date;
    private ProductResponseDto detail;
    private Integer category;
    private Double price;

    public PostResponseDto(Integer userId, String date, Integer category, Double price,ProductResponseDto productResponseDto) {
        this.userId = userId;
        this.id_post = Factory.generateId();
        this.date = date;
        this.detail = productResponseDto;
        this.category = category;
        this.price = price;
    }

}
