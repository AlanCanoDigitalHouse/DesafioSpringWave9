package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CalculateRepositoryImpl implements ICalculateRepository{

    @Autowired
    IHouseDAO houseDAO;

    @Override
    public LocationDTO findPriceLocation(String location){

        return null;
    }

    @Override
    public boolean ifDistrictAreaExist(String location) {
        boolean exist = false;
        Optional<LocationDTO> locationTemp = houseDAO.findStratumByLocationName(location);
        if(locationTemp.isPresent())exist = true;
        return exist;
    }


}
