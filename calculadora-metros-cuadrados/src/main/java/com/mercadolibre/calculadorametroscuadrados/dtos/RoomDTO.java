package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RoomDTO {
  /*El ancho del ambiente no puede estar vacío.
El máximo ancho permitido por propiedad es de 25 mts.
  */
  @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp="^[A-Z][A-z|\\s|[0-9]|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String environment_name;

  @NotEmpty(message = "El ancho del ambiente no puede estar vacío.")
  @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private Double environment_width;

  @NotEmpty(message = "El largo del ambiente no puede estar vacío.")
  @Max(value = 25, message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Double environment_length;

  public Double getArea() {
    double result = 0;
    if(this.environment_width != null && this.environment_length != null)
      result = this.environment_width * this.environment_length;
    return result;
  }

}
