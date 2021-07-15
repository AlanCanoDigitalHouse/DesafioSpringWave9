package com.example.desafio1springboot.dtos;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Validated
public class PostInPromoDTO extends PostDTO{

    @AssertTrue
    @NotNull(message = "No puede ser nulo")
    private Boolean hasPromo = true;

    @DecimalMin(value = "0", inclusive = false, message = "No puede ser menor o igual a 0")
    @DecimalMax(value = "1", message = "Debe ser como maximo 1")
    @NotNull(message = "No puede ser nulo")
    private Double discount;

}
