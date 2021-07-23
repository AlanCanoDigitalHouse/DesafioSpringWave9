package com.meli.tasaciones.service;

import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.HabitacionDto;
import com.meli.tasaciones.model.Casa;
import com.meli.tasaciones.model.Habitacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MetrosCuadradosService {

  default double calcularValor(CasaDto casaDto) {
    return calcularTotalMetrosCuadrados(casaDto) * getValorMetroCuadrado(casaDto.getDireccion());
  }

  default double calcularTotalMetrosCuadrados(CasaDto casaDto) {
    Casa casa = fromDtoToModel(casaDto);
    return casa.getHabitaciones().stream().mapToDouble(Habitacion::getMetrosCuadrados).sum();
  }

  default HabitacionDto getHabitacionMasGrande(CasaDto casaDto) {
    Casa casa = fromDtoToModel(casaDto);
    Habitacion habitacion = casa.getHabitaciones().stream().max(Comparator.comparingDouble(Habitacion::getMetrosCuadrados)).get();
    return fromModelToDto(habitacion);
  }

  default Map<HabitacionDto, Double> calcularMetrosCaudradosPorHabitacion(CasaDto casaDto) {
    Casa casa = fromDtoToModel(casaDto);
    return casa.getHabitaciones().stream().collect(Collectors.toMap(entry -> fromModelToDto(entry),
            entry -> entry.getMetrosCuadrados()));
  }

  double getValorMetroCuadrado(String location);

  default Casa fromDtoToModel(CasaDto casaDto) {
    return new Casa(casaDto.getNombre(), casaDto.getDireccion(), fromDtoToModel(casaDto.getHabitaciones()));
  }

  default List<Habitacion> fromDtoToModel(List<HabitacionDto> habitacionDtoList) {
    ArrayList<Habitacion> habitaciones = new ArrayList<>();
    habitacionDtoList.forEach(dto -> habitaciones.add(fromDtoToModel(dto)));
    return habitaciones;
  }

  default Habitacion fromDtoToModel(HabitacionDto habitacionDto) {
    return new Habitacion(habitacionDto.getNombre(), habitacionDto.getAncho(), habitacionDto.getLargo());
  }

  default HabitacionDto fromModelToDto(Habitacion habitacion) {
    return new HabitacionDto(habitacion.getNombre(), habitacion.getAncho(), habitacion.getLargo());
  }
}
