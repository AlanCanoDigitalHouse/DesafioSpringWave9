package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ResponseDTO {

  private String name;
  private double sqm;
  private double price;
  private Map.Entry<String, Double> biggest;
  private Map<String, Double> environments;


}
