package com.mercadolibre.desafiospring.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class PromoPostCountResponse {
    private int userId;
    private String userName;
    private int promoproducts_count;
}
