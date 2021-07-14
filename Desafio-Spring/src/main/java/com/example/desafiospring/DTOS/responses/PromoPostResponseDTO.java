package com.example.desafiospring.DTOS.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PromoPostResponseDTO {
    private Integer id_post;
    private String date;
    private ProductResponseDTO detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;
}
