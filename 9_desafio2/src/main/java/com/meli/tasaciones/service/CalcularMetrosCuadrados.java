package com.meli.tasaciones.service;

import com.meli.tasaciones.model.Casa;
import com.meli.tasaciones.model.Habitacion;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public interface CalcularMetrosCuadrados {

  public default double calcularValor(Casa casa) {
    return calcularTotalMetrosCuadrados(casa) * getValorMetroCuadrado(casa.getDireccion());
  }

  public default double calcularTotalMetrosCuadrados(Casa casa) {
    return casa.getHabitaciones().stream().mapToDouble(Habitacion::getMetrosCuadrados).sum();
  }

  public default Habitacion getHabitacionMasGrande(Casa casa) {
    return casa.getHabitaciones().stream().max(Comparator.comparingDouble(Habitacion::getMetrosCuadrados)).get();
  }

  public default Map<Habitacion, Double> calcularMetrosCaudradosPorHabitacion(Casa casa) {
    return casa.getHabitaciones().stream().collect(Collectors.toMap(entry -> entry, entry -> entry.getMetrosCuadrados()));
  }

  public double getValorMetroCuadrado(String location);
}
