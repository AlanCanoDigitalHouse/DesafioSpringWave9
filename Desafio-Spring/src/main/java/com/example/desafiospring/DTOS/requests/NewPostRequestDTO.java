package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class NewPostRequestDTO {
    @NotNull(message = "userId is mandatory")
    private Integer userId;
    @NotNull(message = "date is mandatory")
    @NotBlank(message = "date is mandatory")
    private String date;
    @NotNull(message = "detail is mandatory")
    @Valid
    private ProductRequestDTO detail;
    @NotNull(message = "category is mandatory")
    private Integer category;
    @NotNull(message = "price is mandatory")
    @DecimalMin(value = "0",message = "price must be positive")
    private Double price;


}
