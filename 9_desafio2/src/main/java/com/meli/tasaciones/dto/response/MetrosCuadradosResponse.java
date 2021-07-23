package com.meli.tasaciones.dto.response;

import com.meli.tasaciones.dto.HabitacionDto;
import lombok.Data;

import java.util.Map;

@Data
public class MetrosCuadradosResponse {
  private double metrosTotales;
  private double valorDeLaCasa;
  private HabitacionDto habitacionMasGrande;
  private Map<String, Double> metrosCuadradosPorHabitacion;

  public MetrosCuadradosResponse(double metrosTotales, double valorDeLaCasa, HabitacionDto habitacionMasGrande,
                                 Map<String, Double> metrosCuadradosPorHabitacion) {
    this.metrosTotales = metrosTotales;
    this.valorDeLaCasa = valorDeLaCasa;
    this.habitacionMasGrande = habitacionMasGrande;
    this.metrosCuadradosPorHabitacion = metrosCuadradosPorHabitacion;
  }
}
