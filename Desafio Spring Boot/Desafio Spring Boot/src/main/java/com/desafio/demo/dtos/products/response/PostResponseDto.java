package com.desafio.demo.dtos.products.response;

import com.desafio.demo.dtos.products.request.DetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostResponseDto {

    private String date;
    private DetailDto detail;
    private int category;
    private double price;
}
