package com.mercadolibre.calculadorametroscuadrados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
  @NotNull(message = "El nombre del ambiente no puede estar vacío")
  @NotEmpty(message = "El nombre del ambiente no puede estar vacío")
  @Pattern(regexp = "^[A-Z].*", message = "El nombre del ambiente debe comenzar con mayúscula")
  @Size(min = 2, max = 30, message = "La longitud del nombre del ambiente no puede superar los 30 caracteres")
  @JsonProperty("environment_name")
  private String name;

  @NotNull(message = "El ancho del ambiente no debe estar vacío")
  @Max(value = 25, message = "El máximo ancho de un ambiente permitido es de 25 mts")
  @JsonProperty("environment_width")
  private Integer width;

  @NotNull(message = "El largo del ambiente no debe estar vacío")
  @Max(value = 33, message = "El máximo largo de un ambiente permitido es de 33 mts")
  @JsonProperty("environment_length")
  private Integer length;


  public Integer getSquareFeet() {
    Integer result = 0;
    if(this.width != null && this.length != null)
      result = this.width * this.length;
    return result;
  }
}
