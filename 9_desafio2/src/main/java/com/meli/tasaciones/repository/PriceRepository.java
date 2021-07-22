package com.meli.tasaciones.repository;

import com.meli.tasaciones.dto.PriceDTO;

import java.util.Optional;

public interface PriceRepository {
  Optional<PriceDTO> getLocationPrice(String location);
}
