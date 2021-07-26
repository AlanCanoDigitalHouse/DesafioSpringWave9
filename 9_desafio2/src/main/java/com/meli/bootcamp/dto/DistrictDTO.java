package com.meli.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistrictDTO {

    @NotEmpty(message = "El barrio no puede estar vacío")
    @Length(max = 50, message = "La longitud del barrio no puede superar los 45 caracteres")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede ser vacío")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private Double district_price;
}
