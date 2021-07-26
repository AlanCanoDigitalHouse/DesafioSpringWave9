package com.example.desafio2.repositories;

import com.example.desafio2.dtos.DistrictDTO;
import com.example.desafio2.exceptions.BadRequestException;
import com.example.desafio2.utils.DistrictGenerator;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DistrictRepository implements  IDistrict{
    private List<DistrictDTO> districtDTOS;

    public DistrictRepository() {
        this.districtDTOS = DistrictGenerator.getDistrictFile("district");
    }

    @Override
    public DistrictDTO getByName(String name) {
        Optional<DistrictDTO> district = districtDTOS.stream()
                .filter(districtDTO -> districtDTO.getDistrict_name().equals(name))
                .findFirst();
        if (district.isPresent()){
            return district.get();
        }else {
            throw new BadRequestException("District not found");
        }
    }
}
