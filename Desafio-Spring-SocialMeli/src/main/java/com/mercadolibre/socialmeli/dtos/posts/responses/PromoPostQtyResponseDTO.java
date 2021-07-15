package com.mercadolibre.socialmeli.dtos.posts.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromoPostQtyResponseDTO {

    private Integer userId;
    private String userName;
    private Integer promoproducts_count;

}
