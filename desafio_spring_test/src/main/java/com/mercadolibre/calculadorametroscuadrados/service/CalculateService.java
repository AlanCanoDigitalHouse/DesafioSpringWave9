package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import com.mercadolibre.calculadorametroscuadrados.repositories.ICalculateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.mercadolibre.calculadorametroscuadrados.utils.UtilsHome.calculateRoomSquareFeet;

@Service
public class CalculateService implements ICalculateService{

  @Autowired
  ICalculateRepository repository;

  /* TODO: calculateHome permite retornar un HouseResponseDTO
  *   con la informacion requerida por el cliente,
  *   valida si el barrio existe en la db para poder hacer los calculos
  *   correspondientes, en caso contrario retorna una excepción */
  @Override
  public HouseResponseDTO calculateHome(HouseDTO house) throws DataNotFound {
    if(repository.ifDistrictAreaExist(house.getDistrict_name())){
      HouseResponseDTO response = new HouseResponseDTO();
      response.setProp_name(house.getProp_name());
      response.setEnvironments(new ArrayList<>());
      for (EnvironmentDTO env : house.getEnvironments()){
        response.getEnvironments().add(new EnvironmentResponseDTO(env.getEnvironment_name(),env.getSquareFeet())); // US-0004
      }
      calculateRoomSquareFeet(house, response);// US-0001 & US-0002
      response.setProp_price(house.getDistrict_price() * response.getProp_area());
      repository.saveHouse(house,response);
      return response;
    } else throw new DataNotFound("NotExist","District does not exits");
  }
}
