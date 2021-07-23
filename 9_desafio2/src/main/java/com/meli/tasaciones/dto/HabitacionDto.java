package com.meli.tasaciones.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class HabitacionDto {
  @NotNull(message = "El nombre de la habitacion no puede ser null")
  @NotEmpty(message = "El nombre de la habitacion no puede estar vacio")
  private String nombre;
  @Min(message = "El ancho de la habitacion debe ser mayor a 0", value = 1)
  private double ancho;
  @Min(message = "El largo de la habitacion debe ser mayor a 0", value = 1)
  private double largo;

  public HabitacionDto(String nombre, double ancho, double largo) {
    this.nombre = nombre;
    this.ancho = ancho;
    this.largo = largo;
  }
}
