package com.mercadolibre.calculadorametroscuadrados.dto.responses;

import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HouseResponseDTO  {
  private Double squareFeet;
  private Double price;
  private RoomDTO biggest;
  private List<RoomResponseDTO> rooms;
}
