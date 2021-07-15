package com.mercadolibre.social_meli.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromoCountResponseDTO {

    private Integer userId;
    private String userName;
    private Integer promoproducts_count;

}
