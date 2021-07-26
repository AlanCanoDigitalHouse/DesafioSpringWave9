package com.meli.joescaos.desafiotesting.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class HouseDTO {
  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
          message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(min = 3, max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotNull(message = "El barrio no puede estar vacío.")
  @Size(min = 2, max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private double district_price;

  @Valid
  @NotNull(message = "La lista de ambientes no puede estar vacía")
  private List<RoomDTO> rooms;
}
