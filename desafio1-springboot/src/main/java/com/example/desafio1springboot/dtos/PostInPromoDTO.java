package com.example.desafio1springboot.dtos;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Validated
public class PostInPromoDTO extends PostDTO{

    @AssertTrue
    @NotNull(message = "No puede ser nulo")
    private Boolean hasPromo = true;

    @DecimalMin(value = "0.05", message = "Debe ser como minimo 0.05")
    @DecimalMax(value = "1", message = "Debe ser como maximo 1")
    @NotNull(message = "No puede ser nulo")
    private Double discount;

}
