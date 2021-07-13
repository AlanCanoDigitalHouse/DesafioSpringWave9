package com.example.desafiospring.dtos.response;

import com.example.desafiospring.utils.Factory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponseDto {
    @JsonIgnore
    private Integer userId;
    private Integer id_post;
    private String date;
    private List<ProductResponseDto> detail;
    private Integer category;
    private Double price;

    public PostResponseDto(Integer userId, String date, Integer category, Double price) {
        this.userId = userId;
        this.id_post = Factory.generateId();
        this.date = date;
        this.detail = new ArrayList<>();
        this.category = category;
        this.price = price;
    }

    public void addProduct(ProductResponseDto product){
        this.detail.add(product);
    }
}
