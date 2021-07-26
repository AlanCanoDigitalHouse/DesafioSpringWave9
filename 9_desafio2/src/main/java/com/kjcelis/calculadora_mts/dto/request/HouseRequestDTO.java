package com.kjcelis.calculadora_mts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "[A-Z](\\p{Alpha}||\\s)+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;
    @NotBlank(message = "El barrio no puede estar vacío")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000U$S.")
    private double district_price;
    @NotEmpty(message = "Esta coleccion esta vacia")
    private List<@Valid EnvironmentDTO> environments;

}

