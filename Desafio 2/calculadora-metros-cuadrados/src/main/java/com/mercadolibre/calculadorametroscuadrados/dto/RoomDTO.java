package com.mercadolibre.calculadorametroscuadrados.dto;



import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RoomDTO {

  ///@Pattern(regexp = "^[A-Z]",message = "El nombre del ambiente debe comenzar con mayúscula.")
  @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String environment_name;

  @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
  @NotEmpty(message = "El largo del ancho no puede estar vacío.")
  private Double environment_width;

  @Max(value = 25, message = "El máximo largo permitido por propiedad es de 33 mts.")
  @NotEmpty(message = "El largo del ambiente no puede estar vacío.")
  private Double environment_length;

}
