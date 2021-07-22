package com.meli.tasaciones.service;

import com.meli.tasaciones.dto.PriceDTO;
import com.meli.tasaciones.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculadoraMetrosCuadradosImp implements CalcularMetrosCuadrados {

  private final static double VALOR_METRO_CUADRADO_DOLARES = 800;
  private final PriceRepository priceRepository;

  public CalculadoraMetrosCuadradosImp(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  @Override
  public double getValorMetroCuadrado(String location) {
    Optional<PriceDTO> locationPrice = priceRepository.getLocationPrice(location);
    return locationPrice.isPresent() ? locationPrice.get().getPrice() : VALOR_METRO_CUADRADO_DOLARES;
  }
}
