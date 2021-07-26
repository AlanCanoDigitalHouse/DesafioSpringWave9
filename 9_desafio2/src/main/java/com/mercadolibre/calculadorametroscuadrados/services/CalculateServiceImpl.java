package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;
import com.mercadolibre.calculadorametroscuadrados.handlers.CalculateHandler;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

  PriceRepository priceRepository;

  public CalculateServiceImpl(PriceRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  /**
   * Process the requestHouse and with its information creates and returns a HouseResponseDto
   * Preconditions: HouseRequestDTO house already validated.
   * Postconditions: returns a HouseResponse or an error in case of invalid input on house location
   */
  public HouseResponseDTO calculate(HouseRequestDTO house) throws LocationNotFound, IncorrectLocationPrice {
    HouseResponseDTO response = new HouseResponseDTO();
    String name = house.getName();
    response.setName(name);
    Integer locationPrice = CalculateHandler.getLocationPrice(house, priceRepository);
    Integer squareMetters = CalculateHandler.setRooms(house,response);
    response.setSquareMeters(squareMetters);
    Integer price = CalculateHandler.calculatePrice(locationPrice,squareMetters);
    response.setPrice(price);
    return response;
  }

}
