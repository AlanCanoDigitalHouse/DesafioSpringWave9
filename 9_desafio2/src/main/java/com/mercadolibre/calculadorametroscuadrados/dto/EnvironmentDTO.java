package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@Validated

public class EnvironmentDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Size(max=30, message = "La longitud del nombre no puede superar los 30 caracteres")
  private String  enviroment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @DecimalMax(value = "25.0", message ="El máximo ancho permitido por propiedad es de 25 mts.")
  private Double enviroment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Double enviroment_length;

  public Double getSquareFeet() {
    Double result = 0.0;
    if(this.enviroment_width != null && this.enviroment_length != null)
      result = this.enviroment_width * this.enviroment_length;
    return result;
  }
}
