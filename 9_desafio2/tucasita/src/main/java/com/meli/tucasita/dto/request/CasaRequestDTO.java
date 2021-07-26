package com.meli.tucasita.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.utils.Constant;
import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasaRequestDTO {

    @NotBlank(message = Constant.CASA_VACIA)
    @Size(max = 30, message = Constant.CASA_LONGITUD)
    @Pattern(regexp = "^[A-Z].*", message = Constant.CASA_FORMAT_MAYUS)
    @JsonProperty("prop_name")
    private String nombre;

    @NotBlank(message = Constant.DISTRITO_VACIO)
    @Size(max = 45, message = Constant.DISTRITO_LONGITUD)
    @JsonProperty("district_name")
    private String distrito;

    @DecimalMax(value="4000", message = Constant.PRECIO_MAXIMO)
    @DecimalMin(value="1", message = Constant.PRECIO_MINIMO)
    @NotNull(message = Constant.PRECIO_VACIO)
    @JsonProperty("district_price")
    private Double district_price;

    @NotNull(message = Constant.HABITACIONES_VACIAS)
    @Valid
    @JsonProperty("environment")
    private List<Habitacion> habitacion;

}
