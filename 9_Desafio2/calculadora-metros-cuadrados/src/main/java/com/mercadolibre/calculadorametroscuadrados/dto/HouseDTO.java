package com.mercadolibre.calculadorametroscuadrados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {


  @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúsculas")
  @Size(min = 1, max = 30, message = "El nombre de la propiedad no debe superar los 30 caracteres")
  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío")
  @NotNull(message = "El nombre de la propiedad no puede estar vacío")
  @JsonProperty("prop_name")
  private String name;

  @Valid
  @JsonProperty("district")
  private PriceDTO address;

  @Valid
  @JsonProperty("environments")
  private List<RoomDTO> rooms;
}
