package com.mercadolibre.desafio.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
