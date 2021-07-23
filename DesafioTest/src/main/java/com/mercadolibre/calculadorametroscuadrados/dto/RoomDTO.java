package com.mercadolibre.calculadorametroscuadrados.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class RoomDTO {

  @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
  @NotNull(message = "El nombre del ambiente no puede estar vacío.")
  @Pattern(regexp="^[A-Z].*$", message="El nombre del ambiente debe comenzar con mayúscula.")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
  @JsonProperty("environment_name")
  private String name;

  @JsonProperty("environment_square_meter")
  private Double squareMeter;

  @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
  @DecimalMin( value = "0.1", message = "El mínimo ancho permitido por propiedad es de 0.1 mts.")
  @NotNull(message = "El ancho del ambiente no puede estar vacío.")
  @JsonProperty("environment_width")
  private Double width;

  @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
  @DecimalMin( value = "0.1", message = "El mínimo largo permitido por propiedad es de 0.1 mts.")
  @NotNull(message = "El largo del ambiente no puede estar vacío.")
  @JsonProperty("environment_length")
  private Double length;

  public Double calculateSquareMeter() {
    this.setSquareMeter(this.getLength()*this.getWidth());
    return this.getSquareMeter();
  }

}
