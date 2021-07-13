package com.example.desafiospring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountPromoDto {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
