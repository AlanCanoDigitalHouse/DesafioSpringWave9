package com.mercadolibre.desafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseCountFollowers {

    private Integer userId;
    private String userName;
    private Integer followers_count;
}
