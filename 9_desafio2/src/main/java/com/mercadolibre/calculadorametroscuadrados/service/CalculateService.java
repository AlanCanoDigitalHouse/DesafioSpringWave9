package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.NotFoundEnvironmentException;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;
import com.mercadolibre.calculadorametroscuadrados.repository.IEnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalculateService implements ICalculateService {
  IEnvironmentRepository environmentRepository;

  List<EnvironmentResponseDTO> environmentResponses;

  public CalculateService(IEnvironmentRepository environmentRepository) {
    this.environmentRepository = environmentRepository;
  }

  @Override
  public HouseResponseDTO calculate(HouseDTO house) {
    // validate name found in json
    validatePropertyName(house.getDistrict().getDistrict_name());

    HouseResponseDTO response = new HouseResponseDTO(house);// antes de cambio
//    HouseResponseDTO response = new HouseResponseDTO();// antes de cambio
    calculateRoomSquareFeet(house, response);
    response.setPrice(calculatePrice(response.getSquareFeet(),house.getDistrict().getDistrict_price()));
    return response;
  }

  private void calculateRoomSquareFeet(HouseDTO house, HouseResponseDTO response) {
    environmentResponses = new ArrayList<>();
    Double totalSquareFeet = 0.0;
    EnvironmentDTO biggest = null;
    Double maxRoom = 0.0;
    for (EnvironmentDTO room : house.getEnvironments()) {
      Double squareFeet = room.getSquareFeet();
      totalSquareFeet += squareFeet;
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
      environmentResponses.add(EnvironmentResponseDTO.builder().environment_name(room.getEnvironment_name()).environment_width(room.getEnvironment_width()).environment_length(room.getEnvironment_length()).squareFeet(room.getSquareFeet()).build());
    }

    response.setEnvironments(environmentResponses);

    response.setSquareFeet(totalSquareFeet);

    response.setBiggest(biggest);
  }

  @Override
  public double calculatePrice(Double result,Double price) {
    return result * price;
  }

  @Override
  public void validatePropertyName(String prop_name){
    Optional<EnvironmentModel> foundEnvironment = environmentRepository.findEnvironment(prop_name);
    if (foundEnvironment.isEmpty())
      throw new NotFoundEnvironmentException("El barrio ingresado no existe en nuestro repositorio");
  }
}
