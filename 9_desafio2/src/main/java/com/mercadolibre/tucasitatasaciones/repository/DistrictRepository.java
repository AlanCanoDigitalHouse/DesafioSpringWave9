package com.mercadolibre.tucasitatasaciones.repository;

import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepository extends JSONRepository<DistrictDTO> implements IDistrictRepository {

    private static final String DATA_DIR = "classpath:static/districts.json";

    public DistrictRepository() {
        super(DATA_DIR, DistrictDTO.class);
    }

    @Override
    public DistrictDTO getDistrictByName(String name) {
        var districts = this.getAllDistricts();
        var requiredDistrict = districts.stream().filter(
                d -> d.getName().equals(name)
        ).findFirst();

        if (requiredDistrict.isPresent()) {
            return requiredDistrict.get();
        } else {
            throw new ResourceNotFoundException("Required district does not exist.");
        }
    }

    @Override
    public List<DistrictDTO> getAllDistricts() {
        return this.getData();
    }
}
