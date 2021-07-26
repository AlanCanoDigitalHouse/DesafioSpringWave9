package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp = "([A-Z]|[0-9])[\\s|0-9A-Za-zñóíáéúÁÓÉÍÚ]*$",
          message = "El nombre del ambiente debe comenzar con mayúscula.")
  private String name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @DecimalMax(value = "25", message = "El máximo ancho permitido por propiedad es de 25 mts.")
  @DecimalMin(value = "1.5", message = "El mimino ancho permitido por propiedad es de 1.5 mts")
  private Double width;

  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @DecimalMax(value = "33", message = "El máximo largo permitido por propiedad es de 33 mts.")
  @DecimalMin(value = "1.5", message = "El mimino largo permitido por propiedad es de 1.5 mts")
  private Double length;

  private Double squareFeet;

}
