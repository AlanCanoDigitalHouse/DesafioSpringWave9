package com.mercadolibre.squaremeter.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class District {
    @NotNull(message = "El nombre del barrio es requerido")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres.", max = 45)
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del barrio debe comenzar con mayúscula.")
    private String district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private Double district_price;
}
