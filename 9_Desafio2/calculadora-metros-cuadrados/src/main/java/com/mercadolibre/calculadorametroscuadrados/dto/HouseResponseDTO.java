package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class HouseResponseDTO extends HouseDTO {
  private Integer squareFeet;
  private Integer price;
  private RoomDTO biggest;
}
