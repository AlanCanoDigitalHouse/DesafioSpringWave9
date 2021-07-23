package com.meli.tasaciones.model;

import lombok.Data;
import java.util.List;

@Data
public class Casa {

  private String nombre;
  private String direccion;
  private List<Habitacion> habitaciones;

  public Casa(String nombre, String direccion, List<Habitacion> habitaciones) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.habitaciones = habitaciones;
  }
}
