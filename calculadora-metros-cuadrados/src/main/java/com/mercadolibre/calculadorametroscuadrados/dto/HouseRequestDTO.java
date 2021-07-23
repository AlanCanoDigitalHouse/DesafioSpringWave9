package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="^[A-Z][A-z|\\s|[0-9]|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotEmpty(message = "El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

  @NotEmpty(message = "El precio de un barrio no puede estar vacío.")
  @Positive(message = "El precio de un barrio no puede ser negativo ni cero.")
  @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private double district_price;

  @NotEmpty(message = "La cantidad de cuartos no puede ser cero.")
  private List<RoomDTO> rooms;



  public RoomDTO getBiggestRoom() {
    return rooms.stream()
            .max(Comparator.comparing(RoomDTO::getSquareFeet))
            .orElseThrow(NoSuchElementException::new);
  }

  public Double calculateHouseSquareFeet() {
    return rooms.stream()
            .map(RoomDTO::getSquareFeet)
            .reduce( (acum, area) -> acum+=area )
            .get();
  }

  public List<RoomAreaDTO> getRoomAreasDTOs() {
    return rooms
            .stream()
            .map( r -> new RoomAreaDTO(r.getEnvironment_name(), r.getSquareFeet()) )
            .collect(Collectors.toList());
  }
}