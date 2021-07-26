package com.mercadolibre.calculadorametroscuadrados.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {

  @NotNull(message = "El nombre del ambiente no puede estar vacío.")
  @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
  @Size(max=30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @Pattern(regexp = "^[A-Z][A-Za-z0-9 -ñáéíóú]*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
  private String roomName;

  @NotNull(message = "El ancho del ambiente no puede estar vacio.")
  @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts")
  @Min(value = 1, message = "El mínimo ancho permitido por propiedad es de 1 mt")
  private Integer width;


  @NotNull(message = "El largo del ambiente no puede estar vacio.")
  @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts")
  @Min(value = 1, message = "El mínimo largo permitido por propiedad es de 1 mt")
  private Integer length;

}
