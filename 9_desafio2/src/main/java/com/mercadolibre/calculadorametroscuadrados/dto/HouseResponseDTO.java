package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;

import java.util.List;

@Data
public class HouseResponseDTO {

  private String prop_name;
  private DistrictDTO district;
  private List<EnvironmentResponseDTO> environments;
  private Double squareFeet;
  private Double price;
  private EnvironmentResponseDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict(house.getDistrict());
  }
}
