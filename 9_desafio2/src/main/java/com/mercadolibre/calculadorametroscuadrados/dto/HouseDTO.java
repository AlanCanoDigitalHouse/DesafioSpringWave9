package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseDTO {
  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(message = "El nombre de la propiedad debe comenzar con mayúscula.", regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotEmpty(message = "El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 USD")
  private Double district_price;
  private @Valid List<RoomDTO> enviroments;
}
