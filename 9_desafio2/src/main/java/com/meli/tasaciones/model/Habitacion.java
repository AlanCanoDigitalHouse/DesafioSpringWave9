package com.meli.tasaciones.model;

import lombok.Data;

@Data
public class Habitacion {

  private String nombre;
  private double ancho;
  private double largo;

  public Habitacion(String nombre, double ancho, double largo) {
    this.nombre = nombre;
    this.ancho = ancho;
    this.largo = largo;
  }

  public double getMetrosCuadrados() {
    return ancho * largo;
  }
}
