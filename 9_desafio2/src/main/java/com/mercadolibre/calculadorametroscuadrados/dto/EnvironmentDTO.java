package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@Builder
public class EnvironmentDTO {

  @NotNull(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp = "(^[A-Z]).*",message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Length(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private final String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @DecimalMax( value = "25",message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private final Double environment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @DecimalMax( value = "33",message = "El máximo largo permitido por propiedad es de 33 mts.")
  private final Double environment_length;

  public Double getSquareFeet() {
    double result = 0.0;
    if(this.environment_width != null && this.environment_length != null)
      result = this.environment_width * this.environment_length;
    return result;
  }

  public String getEnvironment_name() {
    return environment_name;
  }

  public Double getEnvironment_width() {
    return environment_width;
  }

  public Double getEnvironment_length() {
    return environment_length;
  }
}
