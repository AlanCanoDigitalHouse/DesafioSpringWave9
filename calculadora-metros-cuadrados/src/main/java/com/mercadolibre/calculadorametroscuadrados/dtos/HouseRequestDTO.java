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

  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp="^[A-Z][A-z|\\s|[0-9]|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú|']*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  private String prop_name;

  @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
  @NotEmpty(message = "El barrio no puede estar vacío.")
  private String district_name;

  @NotNull(message = "El precio de un barrio no puede estar vacío.")
  @Positive(message = "El precio de un barrio no puede ser negativo ni cero.")
  @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
  private Double district_price;

  @NotEmpty(message = "La cantidad de cuartos no puede ser cero.")
  private List<RoomDTO> rooms;



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
    this.district_name = null;
    return this;
  }
  public HouseRequestDTO districtNameEmptyString() {
    this.district_name = "";
    return this;
  }
  public HouseRequestDTO propNameOfLength40() {
    this.prop_name = "Lorem ipsum dolor sit amet consectetuer ";
    return this;
  }
  public HouseRequestDTO districtNameOfLength50() {
    this.district_name = "Lorem ipsum dolor sit amet consectetuer adipiscina";
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
  public HouseRequestDTO withOneRoom() {
    rooms.remove(2);
    rooms.remove(1);
    return this;
  }
  public HouseRequestDTO withTwoRooms() {
    rooms.remove(2);
    return this;
  }
  public HouseRequestDTO inDistrict(String district) {
    this.district_name = district;
    return this;
  }
  public HouseRequestDTO firstRoomWithNegativeWidth() {
    rooms.get(0).setEnvironment_width(-10.0);
    return this;
  }

}