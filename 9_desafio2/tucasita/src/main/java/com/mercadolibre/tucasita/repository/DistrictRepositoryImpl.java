package com.mercadolibre.tucasita.repository;

import com.mercadolibre.tucasita.domain.District;
import com.mercadolibre.tucasita.utils.DistrictFileHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

    private List<District> districtList;

    public DistrictRepositoryImpl() {
        this.districtList = DistrictFileHandler.loadDistricts();
    }

    @Override
    public District findByName(String name) {
        return districtList.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
