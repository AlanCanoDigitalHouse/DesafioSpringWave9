package com.mercadolibre.desafio.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseCountPromo {

    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}
