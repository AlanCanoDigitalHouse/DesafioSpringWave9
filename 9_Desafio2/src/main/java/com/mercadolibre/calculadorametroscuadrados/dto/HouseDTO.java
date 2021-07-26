package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
public class HouseDTO {

  @NotNull(message = "El nombre de la propiedad no puede estar vacio.")
  @Pattern(regexp = "(^[A-Z]).*",message = "El nombre de la propiedad debe comenzar con mayuscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String name;

  @Valid
  private DistrictDTO district;

  @Valid
  private List<EnviromentDTO> enviroments;

  public HouseDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DistrictDTO getDistrict() {
    return district;
  }

  public void setDistrict(DistrictDTO districtDTO) {
    this.district = districtDTO;
  }

  public List<EnviromentDTO> getEnviroments() {
    return enviroments;
  }

  public void setEnviroments(List<EnviromentDTO> enviroments) {
    this.enviroments = enviroments;
  }
}
