package com.example.tucasitatasacciones.dto.request;
import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DistrictRequestDTO {

    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    @NotEmpty
    @NotNull(message = "El barrio no puede estar vacio")
    private String district_name;


    @NotNull(message = "El precio no puede ser vacio")
    @DecimalMin(value = "0", message = "El precio no puede ser menor a 0")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido\n" +
            "por metro cuadrado no puede superar los 4000 U$S.")
    private double district_price;
}
