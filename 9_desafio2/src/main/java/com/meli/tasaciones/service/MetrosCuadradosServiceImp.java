package com.meli.tasaciones.service;

import com.meli.tasaciones.dto.CasaDto;
import com.meli.tasaciones.dto.HabitacionDto;
import com.meli.tasaciones.exception.CalcularValorException;
import com.meli.tasaciones.exception.LocationValidationException;
import com.meli.tasaciones.exception.MetrosCuadradosException;
import com.meli.tasaciones.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetrosCuadradosServiceImp implements MetrosCuadradosService {

  private final LocationRepository locationRepository;

  public MetrosCuadradosServiceImp(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public Double calcularValor(CasaDto casaDto) throws MetrosCuadradosException {
    validarCasa(casaDto);
    return calcularTotalMetrosCuadrados(casaDto) * getValorMetroCuadrado(casaDto.getDireccion(), casaDto.getPrice());
  }

  public Double calcularTotalMetrosCuadrados(CasaDto casaDto) throws CalcularValorException {
    validarCasa(casaDto);
    return casaDto.getHabitaciones().stream().mapToDouble(habitacion -> habitacion.getAncho() * habitacion.getLargo()).sum();
  }

  public HabitacionDto getHabitacionMasGrande(CasaDto casaDto) throws CalcularValorException {
    validarCasa(casaDto);
    HabitacionDto habitacionDto = casaDto.getHabitaciones().stream().max(Comparator.comparingDouble(habitacion -> habitacion.getLargo() * habitacion.getAncho())).get();
    return habitacionDto;
  }

  public Map<HabitacionDto, Double> calcularMetrosCaudradosPorHabitacion(CasaDto casaDto) throws CalcularValorException {
    validarCasa(casaDto);
    return casaDto.getHabitaciones().stream().collect(Collectors.toMap(entry -> entry,
            entry -> entry.getAncho() * entry.getLargo()));
  }

  @Override
  public Double getValorMetroCuadrado(String location, Double precioPorMetroCuadrado) throws MetrosCuadradosException {
    Double locationPrice = locationRepository.getLocationPrice(location);
    if (!locationPrice.equals(precioPorMetroCuadrado)) {
      throw new LocationValidationException("El precio del barrio no coincide con el del repositorio");
    }
    return locationPrice;
  }

  private void validarCasa(CasaDto casaDto) throws CalcularValorException {
    if (casaDto.getHabitaciones().isEmpty()) {
      throw new CalcularValorException("La casa no tiene habitaciones");
    }
    for (HabitacionDto habitacionDto : casaDto.getHabitaciones()) {
      if (habitacionDto.getLargo() < 1.0 || habitacionDto.getAncho() < 1.0) {
        throw new CalcularValorException("El largo y ancho de los ambientes debe ser positivo");
      }
    }

  }
}
