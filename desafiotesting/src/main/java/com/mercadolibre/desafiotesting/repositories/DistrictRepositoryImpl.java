package com.mercadolibre.desafiotesting.repositories;

import com.mercadolibre.desafiotesting.exceptions.DistrictException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    List<String> names = new ArrayList<>();

    public DistrictRepositoryImpl() {
        names.add("Barrio flores");
        names.add("Barrio sol");
        names.add("Barrio luna");
    }

    @Override
    public String findDistrictByName(String name) throws DistrictException {
        return names.stream().filter(b -> b.equals(name)).findFirst().orElseThrow(() -> new DistrictException(DistrictException.DISTRICT_NOT_FOUND));
    }
}
