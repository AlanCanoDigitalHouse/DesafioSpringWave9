package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.EnvironmentRepoDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;
import com.mercadolibre.calculadorametroscuadrados.repositories.ICalculateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.calculadorametroscuadrados.utils.UtilsHome.calculateRoomSquareFeet;

@Service
public class CalculateService implements ICalculateService{

  @Autowired
  ICalculateRepository repository;

  @Override
  public HouseResponseDTO calculateHome(HouseDTO house) throws DataNotFound {
    if(repository.ifDistrictAreaExist(house.getDistrict_name())){
      HouseResponseDTO response = new HouseResponseDTO();
      for (EnvironmentDTO env : house.getEnvironments()){
        response.getEnvironments().add(new EnvironmentResponseDTO(env.getEnvironment_name(),env.getSquareFeet())); // US-0004
      }
      calculateRoomSquareFeet(house, response);// US-0001 & US-0002
      response.setProp_price(house.getDistrict_price() * response.getProp_area());
      repositoryHouse(house,response);
      return response;
    } else throw new DataNotFound("Not Exist","District does not exits");
  }

  private void repositoryHouse(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO){
    HouseRepositoryDTO houseRepository = new HouseRepositoryDTO();
    houseRepository.setProp_name(houseDTO.getProp_name());
    houseRepository.setDistrict_name(houseDTO.getDistrict_name());
    houseRepository.setDistrict_price(houseDTO.getDistrict_price());
    houseRepository.setProp_price(houseResponseDTO.getProp_price());
    houseRepository.setProp_area(houseResponseDTO.getProp_area());
    List<EnvironmentRepoDTO> environments = new ArrayList<>();
    for(EnvironmentDTO env: houseDTO.getEnvironments()){
      for(EnvironmentResponseDTO envRes: houseResponseDTO.getEnvironments()){
        if(env.getEnvironment_name().equals(envRes.getEnvironment_name())){
          environments.add(new EnvironmentRepoDTO(env.getEnvironment_name(), env.getEnvironment_width(),env.getEnvironment_length(),envRes.getEnvironment_area()));
        }
      }
    }
    houseRepository.setEnvironments(environments);
  }
}
