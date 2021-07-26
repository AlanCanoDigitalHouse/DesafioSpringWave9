package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class PropiedadDTO {

  @NotBlank (message = "El nombre de la propiedad no puede estar vacío.")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
  @Size(max =30,message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String prop_name;

  private @Valid BarrioDTO district;

  private List<@Valid AmbienteDTO> environments;

}
