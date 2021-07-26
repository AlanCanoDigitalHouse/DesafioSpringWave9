package com.mercadolibre.tucasitaTasaciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class PropertyDTO {

  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp = "[A-Z-ZÀ-Ö][a-zA-ZÀ-ÖØ-öø-ÿ]*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  @NotNull(message = "El barrio no puede estar vacío.")
  @Valid
  private DistrictDTO district;

  @NotEmpty(message = "La propiedad debe contener ambientes.")
  @Valid
  private List<EnvironmentDTO> environments;


}
