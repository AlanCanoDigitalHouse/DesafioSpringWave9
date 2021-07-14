package com.mercadolibre.desafio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.desafio.dtos.requests.RequestDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Post {

    private Integer id_post;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private RequestDetailDto detail;
    private Integer category;
    private Double price;
}
