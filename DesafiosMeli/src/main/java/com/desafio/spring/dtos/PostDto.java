package com.desafio.spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostDto {
    private Integer userId;
    private String date;
    private ProductDto detail;
    private Integer category;
    private Double price;

}
