package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoProductCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
