package com.mercadolibre.desafio.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class RequestDetailDto {

    @NotNull
    @NotBlank
    private String productName;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    @NotBlank
    private String brand;
    @NotNull
    @NotBlank
    private String color;
    @NotNull
    @NotBlank
    private String notes;
}
