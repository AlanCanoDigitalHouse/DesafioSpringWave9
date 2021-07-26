package com.mercadolibre.tucasitaTasaciones.dto.response;

import com.mercadolibre.tucasitaTasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitaTasaciones.dto.EnvironmentDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PropertyResponseDTO {
  private String prop_name;
  private DistrictDTO district;
  private Double squareFeet;
  private Double price;
  private EnvironmentDTO biggest;
  private List<EnvironmentResponseDTO> squareFeetEnvironments;

  public PropertyResponseDTO() {
    this.squareFeetEnvironments = new ArrayList<>();
  }

  public void setBiggest(EnvironmentDTO biggest) {
    this.biggest = biggest;
  }
}
