package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="^[A-Z][A-z|\\s|[0-9]|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú|']*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotEmpty(message = "El barrio no puede estar vacío.")
  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @Positive(message = "El precio de un barrio no puede ser negativo ni cero.")
  @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private Double district_price;

  @Size(min = 1, message = "La cantidad de cuartos no puede ser cero.")
  private List<@Valid RoomDTO> rooms;



  public RoomDTO getBiggestRoom() {
    return rooms.stream()
            .max(Comparator.comparing(RoomDTO::getArea))
            .orElseThrow(NoSuchElementException::new);
  }

  public Double calculateHouseArea() {
    return rooms.stream()
            .map(RoomDTO::getArea)
            .reduce( (acum, area) -> acum+=area )
            .get();
  }

  public List<RoomAreaDTO> getRoomAreasDTOs() {
    return rooms
            .stream()
            .map( r -> new RoomAreaDTO(r.getEnvironment_name(), r.getArea()) )
            .collect(Collectors.toList());
  }

  public HouseRequestDTO propNameNull() {
    this.prop_name = null;
    return this;
  }
  public HouseRequestDTO propNameEmptyString() {
    this.prop_name = "";
    return this;
  }
  public HouseRequestDTO districtNameNull() {
    this.prop_name = null;
    return this;
  }
  public HouseRequestDTO districtNameEmptyString() {
    this.district_name = "";
    return this;
  }
  public HouseRequestDTO propNameOfLength40() {
    this.prop_name = "Lorem ipsum dolor sit amet, consectetuer";
    return this;
  }
  public HouseRequestDTO districtNameOfLength50() {
    this.prop_name = "Lorem ipsum dolor sit amet, consectetuer adipiscin";
    return this;
  }
  public HouseRequestDTO districtPriceGreaterThan4000() {
    this.district_price = 4010.0;
    return this;
  }
  public HouseRequestDTO districtPriceNull() {
    this.district_price = 0.0;
    return this;
  }
  public HouseRequestDTO districtPriceEqualToZero() {
    this.district_price = 0.0;
    return this;
  }
  public HouseRequestDTO districtPriceNegative() {
    this.district_price = -200.0;
    return this;
  }
  public HouseRequestDTO roomsEmpty() {
    this.rooms = new ArrayList<>();
    return this;
  }
  public HouseRequestDTO roomsNull() {
    this.rooms = null;
    return this;
  }
  public List<RoomDTO> rooms() {
    return rooms;
  }

}