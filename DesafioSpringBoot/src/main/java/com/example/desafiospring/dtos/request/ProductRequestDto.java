package com.example.desafiospring.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
public class ProductRequestDto {
    @NotNull
    @Size(min = 4,max = 100)
    private String productName;
    @NotNull
    @Size(min = 1,max = 100)
    private String brand;
    @NotNull
    @Size(min = 1,max = 100)
    private String color;
    @NotNull
    @Size(min=1,max = 200)
    private String notes;
}
