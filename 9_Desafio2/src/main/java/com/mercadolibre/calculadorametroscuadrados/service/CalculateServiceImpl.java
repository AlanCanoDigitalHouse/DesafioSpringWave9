package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.PriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.NotFoundLocation;
import com.mercadolibre.calculadorametroscuadrados.repositories.PriceRepository;

import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

  private final PriceRepository priceRepository;

  public CalculateServiceImpl(PriceRepository priceRepository){
    this.priceRepository = priceRepository;
  }

  /**
   * Metodo para calcular los metros cuadrados, el precio, la habitacion mas grande de una propiedad
   * @param house Propiedad a estudiar
   * @return Resultado de las operaciones
   * @throws NotFoundLocation Si el districto de la propiedad no existe
   */
  @Override
  public HouseResponseDTO calculate(HouseDTO house) throws NotFoundLocation {
    HouseResponseDTO response = new HouseResponseDTO(house);
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(), house));
    return response;
  }

  /**
   * Metodo para calcular metros cuadrados por habitacion, cuatro mas grande y tamaño total de la propiedad
   * @param house Casa a ser procesada
   * @param response Casa con metros cuadrados por habitacion, cuatro mas grande y tamaño total.
   */
  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    Double totalSquareFeet = 0d;
    RoomDTO biggest = null;
    Double maxRoom = 0d;
    for (RoomDTO room : house.getEnvironment()) {
      room.setSquareFeet(room.getWidth()*room.getLength());
      Double squareFeet = room.getSquareFeet();
      totalSquareFeet += squareFeet;
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }
    response.setSquareFeet(totalSquareFeet);
    response.setBiggest(biggest);
  }

  /**
   * Metodo para calcular el precio total de la propiedad
   * @param result Cantidad de metros cuadrados de la casa
   * @param house Casa a ser analizada
   * @return Precio total
   * @throws NotFoundLocation en caso que el districto no exista
   */
  private Double calculatePrice(Double result, HouseDTO house) throws NotFoundLocation {
    PriceDTO priceDTO = priceRepository.findLocation(house.getDistrictName());
    return result * house.getDistrictPrice();
  }

}
