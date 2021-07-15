package com.example.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountPromoPostResponseDto {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
