package com.mercadolibre.socialmedia.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPromoQuantityResponse {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
