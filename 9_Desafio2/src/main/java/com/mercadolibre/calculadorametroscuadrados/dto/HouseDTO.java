package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data

public class HouseDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="([A-Z])\\w+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotNull(message = "Se requiere al menos 1 ambiente en la propiedad.")
  private List<@Valid RoomDTO> rooms;

}
