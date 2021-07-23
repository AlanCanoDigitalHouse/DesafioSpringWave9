package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HouseResponseDTO {
  private String prop_name;
  private Double house_area;
  private Double price;
  private String biggest;
  private List<RoomAreaDTO> room_areas;

}
