package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data

public class HousePriceRequestDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp="([A-Z])\\w+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMin(message = "El precio mínimo admitido es de 1,0 U$S.", value = "1.0")
    @DecimalMax(message = "El precio máximo permitido es de 4000 U$S.", value = "4000.0")
    private Double district_price;


    @NotNull(message = "Se requiere al menos 1 ambiente en la propiedad.")
    private List<@Valid RoomDTO> rooms;

}
