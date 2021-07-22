package com.meli.tasaciones.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class Casa {

  @NotNull(message = "El nombre no puede ser null")
  @NotEmpty(message = "El nombre no puede estar vacio")
  private String nombre;
  private String direccion;
  @NotEmpty(message = "La casa debe tener habitaciones")
  @Valid
  private List<Habitacion> habitaciones;

  public Casa(String nombre, String direccion, List<Habitacion> habitaciones) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.habitaciones = habitaciones;
  }
}
