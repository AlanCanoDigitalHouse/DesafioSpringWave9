package com.example.desafio1.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Product {
    @NotEmpty
    private String productName;
    @NotEmpty
    private String type;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String color;
    @NotEmpty
    private String notes;
}
