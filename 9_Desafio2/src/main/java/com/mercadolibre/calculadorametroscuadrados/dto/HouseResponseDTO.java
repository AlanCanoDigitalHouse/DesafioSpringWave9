package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HouseResponseDTO {

  private String prop_name;
  private Double area;

}
