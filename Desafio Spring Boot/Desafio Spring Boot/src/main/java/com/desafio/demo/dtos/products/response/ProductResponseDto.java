package com.desafio.demo.dtos.products.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductResponseDto {

    private int userId;
    private List<PostResponseDto> posts;



}
