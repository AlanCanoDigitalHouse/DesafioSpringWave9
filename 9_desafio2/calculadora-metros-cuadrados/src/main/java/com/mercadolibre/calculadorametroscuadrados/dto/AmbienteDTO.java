package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
@AllArgsConstructor
public class AmbienteDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max =30,message = "La longitud del nombre no puede superar los 45 caracteres.")
  private String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío")
  @Max(value = 25,message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private Double environment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío")
  @Max(value = 33,message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Double environment_length;
}
