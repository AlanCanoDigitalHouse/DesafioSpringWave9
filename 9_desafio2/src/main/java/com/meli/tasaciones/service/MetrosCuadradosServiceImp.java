package com.meli.tasaciones.service;

import com.meli.tasaciones.exception.LocationNotFoundException;
import com.meli.tasaciones.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class MetrosCuadradosServiceImp implements MetrosCuadradosService {

  private final static double VALOR_METRO_CUADRADO_DOLARES = 800;
  private final LocationRepository locationRepository;

  public MetrosCuadradosServiceImp(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public double getValorMetroCuadrado(String location) {
    try {
      return locationRepository.getLocationPrice(location);
    } catch (LocationNotFoundException e) {
      return VALOR_METRO_CUADRADO_DOLARES;
    }
  }
}
