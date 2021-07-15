package com.mercadolibre.desafio.demo.dtos.response;

import com.mercadolibre.desafio.demo.models.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicsResponseDTO {
    private Integer id_post;
    private String date;
    private ProductModel detail;
    private String category;
    private Double price;

}
