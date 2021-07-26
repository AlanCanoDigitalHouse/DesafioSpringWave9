package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class RoomDTO {
  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp="^[A-Z][A-z|\\s|[0-9]|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú|']*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
  @Positive(message = "El ancho de un ambiente no puede ser negativo ni cero.")
  private Double environment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @Max(value = 25, message = "El máximo largo permitido por propiedad es de 33 mts.")
  @Positive(message = "El largo de un ambiente no puede ser negativo ni cero.")
  private Double environment_length;

  public Double getArea() {
      return this.environment_width * this.environment_length;
  }

}
