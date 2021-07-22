package com.meli.tasaciones.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.tasaciones.dto.PriceDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepositoryJson implements PriceRepository {

  @Override
  public Optional<PriceDTO> getLocationPrice(String location) {
    try {
      List<PriceDTO> prices = loadLocationPrices();
      return prices.stream().filter(p -> p.getLocation().equals(location)).findFirst();
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }


  private List<PriceDTO> loadLocationPrices() throws IOException {
    return jsonToPriceDTOList(ResourceUtils.getFile("classpath:static/location-prices.json"));
  }

  private List<PriceDTO> jsonToPriceDTOList(File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<List<PriceDTO>> typeReference = new TypeReference<>() {
    };
    return objectMapper.readValue(file, typeReference);
  }
}
