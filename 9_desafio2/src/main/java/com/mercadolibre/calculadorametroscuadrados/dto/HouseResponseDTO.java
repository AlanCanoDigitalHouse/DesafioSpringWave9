package com.mercadolibre.calculadorametroscuadrados.dto;


import lombok.*;

import java.util.List;

//@Getter @Setter
@Data
//public class HouseResponseDTO extends HouseDTO {
public class HouseResponseDTO {
  private String prop_name;
  private DistrictDTO district;
  private List<EnvironmentResponseDTO> environments;
  private Double squareFeet;
  private Double price;
  private EnvironmentDTO biggest;

  public HouseResponseDTO(HouseDTO house) {
    this.setProp_name(house.getProp_name());
    this.setDistrict(house.getDistrict());
//    this.setEnvironments(house.getEnvironments());
  }

}
