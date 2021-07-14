package com.mercadolibre.desafio_spring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoProductsCountResponse {
    int userId;
    String userName;
    int promoproducts_count;
}
