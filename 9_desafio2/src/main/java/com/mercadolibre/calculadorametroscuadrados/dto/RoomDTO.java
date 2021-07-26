package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
  @NotNull(message = "El nombre del ambiente no puede estar vacio.")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayuscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String enviroment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacio.")
  @DecimalMax(message = "El maximo ancho permitido por propiedad es de 25 mts.", value = "25")
  private Double enviroment_width;

  @NotNull(message = "El largo del ambiente no puede estar vacio.")
  @DecimalMax(message = "El maximo largo permitido por propiedad es de 33 mts.", value = "33")
  private Double enviroment_length;

  public Double getSquareFeet() {
    Double result = 0.0;
    if(this.enviroment_width != null && this.enviroment_length != null)
      result = this.enviroment_width * this.enviroment_length;
    return result;
  }
}
