package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validations.IFirstValidation;
import com.bootcamp.desafio2.validations.ISecondValidation;
import com.bootcamp.desafio2.validations.IThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class DistrictDto {

    @NotBlank(message = "El barrio no puede estar vacío", groups = IFirstValidation.class)
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres", max = 45, groups = ISecondValidation.class)
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío", groups = IFirstValidation.class)
    @DecimalMin(message = "El precio del metro cuadrado no puede ser menor a cero", value = "0.0", groups = ISecondValidation.class)
    @DecimalMax(message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S", value = "4000.0", groups = IThirdValidation.class)
    private Double district_price;

}
