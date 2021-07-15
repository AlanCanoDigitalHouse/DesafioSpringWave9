package com.example.demo.dtos.response;

import com.example.demo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

public class SimplePostResponseDto extends PostResponseDto {

    public SimplePostResponseDto(int id_post, Date date, Product detail, int category, double price, boolean hasPromo, double discount) {
        super(id_post, date, detail, category, price, hasPromo, discount);
    }
}
