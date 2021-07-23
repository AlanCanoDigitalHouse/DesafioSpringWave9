package com.meli.tasaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@Validated
public class HabitacionDto {
  @NotBlank(message = "El nombre del ambiente no puede estar vacio")
  @Pattern(regexp = "[A-Z]\\p{Alpha}+", message = "El nombre del ambiente debe comenzar con mayuscula")
  @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
  @JsonProperty("environment_name")
  private String nombre;
  @NotNull(message = "El ancho del ambiente no puede estar vacio")
  @DecimalMin(value = "1.0", message = "El ancho del ambiente minimo permitido es de 1.0")
  @DecimalMax(value = "25.0", message = "El maximo ancho permitido por propiedad es de 25 mts")
  @JsonProperty("environment_width")
  private double ancho;
  @NotNull(message = "El largo del ambiente no puede estar vacio")
  @DecimalMin(value = "1.0", message = "El largo del ambiente minimo permitido es de 1.0")
  @DecimalMax(value = "33.0", message = "El maximo largo permitido por propiedad es de 33 mts")
  @JsonProperty("environment_length")
  private double largo;

  public HabitacionDto(String nombre, double ancho, double largo) {
    this.nombre = nombre;
    this.ancho = ancho;
    this.largo = largo;
  }
}
