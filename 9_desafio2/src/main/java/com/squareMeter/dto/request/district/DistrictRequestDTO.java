package com.squareMeter.dto.request.district;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictRequestDTO {
    @NotNull(message = "El barrio no puede estar vacío.")
    @NotEmpty
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    public String district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    @DecimalMin(value = "0", message = "Se intento ingresar un valor negativo")
    public Double district_price;

}
