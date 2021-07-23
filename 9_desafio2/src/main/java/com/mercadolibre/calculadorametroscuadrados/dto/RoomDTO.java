package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class RoomDTO {
  @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(message = "El nombre del ambiente debe comenzar con mayúscula.", regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String enviroment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @Max(value = 25 ,message = "El máximo ancho permitido por propiedad es de 25 mts.")
  private Integer enviroment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts.")
  private Integer enviroment_length;

  public Integer getSquareFeet() {
    Integer result = 0;
    if(this.enviroment_width != null && this.enviroment_length != null)
      result = this.enviroment_width * this.enviroment_length;
    return result;
  }
}
