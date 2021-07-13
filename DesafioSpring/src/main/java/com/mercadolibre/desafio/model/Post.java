package com.mercadolibre.desafio.model;

import com.mercadolibre.desafio.dtos.RequestDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Post {

    private Integer id_post;
    private String date;
    private RequestDetailDto detail;
    private Integer category;
    private Double price;
}
