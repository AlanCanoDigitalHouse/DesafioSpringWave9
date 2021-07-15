package com.mercadolibre.desafio.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PublicPromoModel {

    private Integer id_post;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    private ProductModel detail;
    private Integer category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;
}
