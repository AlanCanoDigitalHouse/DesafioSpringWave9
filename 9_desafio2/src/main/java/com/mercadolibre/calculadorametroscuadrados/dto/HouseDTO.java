package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseDTO {

  @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp = "(^[A-Z]).*",message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Length(max = 30, message =  "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotNull(message = "El parámetro 'district' no puede estar estar vacío.")
  @Valid
  private DistrictDTO district;

  @NotNull(message = "El parámetro 'environments' no puede estar vacío.")
  @Valid
  private List<EnvironmentDTO> environments;
}
