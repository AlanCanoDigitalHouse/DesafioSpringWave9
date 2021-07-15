package com.example.desafiospring.dtos.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDTO {

    @NotNull(message = "productName no puede ser nulo.")
    @NotBlank(message = "El productName no puede estar vacio.")
    private String productName;

    @NotNull(message = "type no puede ser nulo.")
    @NotBlank(message = "type no puede estar vacio.")
    private String type;

    @NotNull(message = "brand no puede ser nulo.")
    @NotBlank(message = "brand no puede estar vacio.")
    private String brand;

    @NotNull(message = "color no puede ser nulo.")
    @NotBlank(message = "color no puede estar vacio.")
    private String color;

    private String notes;

}
