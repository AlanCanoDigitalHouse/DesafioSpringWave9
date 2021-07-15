package com.desafio.demo.dtos.products.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
@AllArgsConstructor
public class DetailDto {


    @NotEmpty
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;


}
