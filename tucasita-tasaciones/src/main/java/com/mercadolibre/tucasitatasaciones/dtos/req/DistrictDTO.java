package com.mercadolibre.tucasitatasaciones.dtos.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String districtName;

    @NotNull(message = "El precio de un barrio no uede estar vacío.")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    @Min(value = 1, message = "El precio mínimo permitido por metro cuadrado no puede ser inferior a 1 U$S.")
    private Double districtPrice;

}
