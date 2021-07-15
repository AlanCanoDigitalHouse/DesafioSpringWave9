package com.mercadolibre.desafio.spring.dtos.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ToString
public class ProductDto {

    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
