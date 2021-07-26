package com.mercadolibre.calculadorametroscuadrados.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class HouseResponseDTO{
  private String name;
  private Integer squareMeters;
  private Integer price;
  private RoomResponseDTO biggest;
  private ArrayList<RoomResponseDTO> infoRooms;

}
