package com.example.casitas.repositories;

import com.example.casitas.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

    List<String> districtNames = new ArrayList<>();

    public DistrictRepositoryImpl() {
        districtNames.add("Palermo");
        districtNames.add("Villa del Parque");
        districtNames.add("Villa Devoto");
        districtNames.add("Flores");
    }

    @Override
    public String findDistrictByName(String districtName) throws DistrictNotFoundException {
        return districtNames.stream().filter(f -> f.equals(districtName)).findFirst().orElseThrow(() -> new DistrictNotFoundException("El barrio no existe"));
    }
}
