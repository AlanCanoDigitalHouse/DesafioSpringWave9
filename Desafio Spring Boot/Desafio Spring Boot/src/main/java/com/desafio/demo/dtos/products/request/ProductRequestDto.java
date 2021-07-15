package com.desafio.demo.dtos.products.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@Validated
public class ProductRequestDto {


    @NotNull
    private int userId;
    private String date;
    @NotNull
    private DetailDto detail;
    private int category;
    @Positive
    private double price;


}
