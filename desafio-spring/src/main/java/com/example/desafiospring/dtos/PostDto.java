package com.example.desafiospring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private Long id_post;
    private String date;
    private ProductDto detail;
    private String category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;

}
