package com.bootcamp.desafio2.services.implementation;

import com.bootcamp.desafio2.dtos.request.HouseDTO;
import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import com.bootcamp.desafio2.dtos.response.HouseResponseDTO;
import com.bootcamp.desafio2.exceptions.DistrictNotExistsException;
import com.bootcamp.desafio2.exceptions.PriceNotMatchException;
import com.bootcamp.desafio2.services.IDistrictService;
import com.bootcamp.desafio2.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private IDistrictService districtService;

    @Override
    public HouseResponseDTO calculateArea(HouseDTO house) throws DistrictNotExistsException, PriceNotMatchException {
        Double totalArea = 0D;
        Double maxArea = 0D;
        EnvironmentDTO aux = new EnvironmentDTO();

        for (EnvironmentDTO room : house.getRooms()) {
            Double area = room.getEnvironment_width() * room.getEnvironment_length();
            room.setSquareMeters(area);
            totalArea = totalArea + area;
            if (area > maxArea) {
                aux = room;
                maxArea = area;
            }
        }
        return new HouseResponseDTO(totalArea,
                totalArea * districtService.findPriceByLocation(house.getDistrict().getDistrict_name(),
                        house.getDistrict().getDistrict_price()),
                aux, house.getRooms());

    }

}
