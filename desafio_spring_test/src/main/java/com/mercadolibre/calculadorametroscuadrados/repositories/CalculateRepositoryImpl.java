package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.EnvironmentDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.EnvironmentRepoDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.repository.HouseRepositoryDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.EnvironmentResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CalculateRepositoryImpl implements ICalculateRepository{

    @Autowired
    IHouseDAO houseDAO;

    @Override
    public boolean ifDistrictAreaExist(String location) {
        boolean exist = false;
        Optional<LocationDTO> locationTemp = houseDAO.findStratumByLocationName(location);
        if(locationTemp.isPresent())exist = true;
        return exist;
    }

    @Override
    public void saveHouse(HouseDTO houseDTO, HouseResponseDTO houseResponseDTO) {
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
        houseDAO.save(houseRepository);
    }

}
