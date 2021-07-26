package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Validated
public class PropertyDTO {

  @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  private String prop_name;

  @NotNull(message = "El barrio no puede estar vacío.")
  private @Valid DistrictDTO district;

  @NotEmpty(message = "La lista de ambientes no puede estar vacía.")
  private List<@Valid EnvironmentDTO> environments;


}
