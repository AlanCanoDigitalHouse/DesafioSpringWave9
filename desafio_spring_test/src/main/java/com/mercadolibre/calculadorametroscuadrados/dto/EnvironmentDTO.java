package com.mercadolibre.calculadorametroscuadrados.dto;

import com.mercadolibre.calculadorametroscuadrados.validators.constraints.IsUpperCaseConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class EnvironmentDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @IsUpperCaseConstraint(message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(min = 1, max = 30,message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @Max(message = "El máximo ancho permitido por propiedad es de 25mts.",value = 25)
  private Double environment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @Max(message = "El máximo largo permitido por propiedad es de 33mts.",value = 33)
  private Double environment_length;

  public Double getSquareFeet() {
    double result = 0.0;
    if(this.environment_width != null && this.environment_length != null)
      result = this.environment_width * this.environment_length;
    return result;
  }
}
