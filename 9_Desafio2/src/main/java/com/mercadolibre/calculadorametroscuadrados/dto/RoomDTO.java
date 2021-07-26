package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated

public class RoomDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp="([A-Z])\\w+", message = "El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  private String environment_name;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @DecimalMin(message = "El ancho mínimo es de 1 m.", value = "1.0")
  @DecimalMax(message = "El máximo ancho permitido por propiedad es de 25 m.", value = "25.0")
  private Double environment_width;

  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @DecimalMin(message = "El ancho mínimo es de 1 m.", value = "1.0")
  @DecimalMax(message = "El máximo ancho permitido por propiedad es de 33 m.", value = "33.0")
  private Double environment_length;

}
