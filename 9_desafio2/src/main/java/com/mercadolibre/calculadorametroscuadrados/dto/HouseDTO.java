package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
public class HouseDTO {
  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayuscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotNull(message = "El barrio no puede estar vacio.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacio.")
  @DecimalMax(message = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S.", value ="4000")
  private Double district_price;

  @Valid
  private List<RoomDTO> rooms;

}
