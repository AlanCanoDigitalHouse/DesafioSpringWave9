package com.meli.tucasita.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.utils.Constant;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {

    @NotBlank(message = Constant.HABITACION_VACIA)
    @Size(max = 30, message = Constant.HABITACION_LONGITUD)
    @Pattern(regexp = "^[A-Z].*", message = Constant.HABITACION_FORMAT_MAYUS)
    @JsonProperty("environment_name")
    private String nombre;

    @NotNull(message = Constant.WIDTH_VACIO)
    @DecimalMax(value="25", message = Constant.WIDTH_MAX)
    @DecimalMin(value="1", message = Constant.WIDTH_MIN)
    @JsonProperty("environment_width")
    private double ancho;

    @NotNull(message = Constant.LENGTH_VACIO)
    @DecimalMax(value="33", message = Constant.LENGTH_MAX)
    @DecimalMin(value="1", message = Constant.LENGTH_MIN)
    @JsonProperty("environment_length")
    private double largo;

}
