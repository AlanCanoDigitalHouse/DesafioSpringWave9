package com.mercadolibre.socialmeli.dtos.Product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountPromoDTO {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
