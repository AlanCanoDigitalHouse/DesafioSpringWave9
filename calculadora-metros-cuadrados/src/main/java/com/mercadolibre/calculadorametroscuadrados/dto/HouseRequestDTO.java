package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {
  private String prop_name;
  private String district_name;
  private double district_price;
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