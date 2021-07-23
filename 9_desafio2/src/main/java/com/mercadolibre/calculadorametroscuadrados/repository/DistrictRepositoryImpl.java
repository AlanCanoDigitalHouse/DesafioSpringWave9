package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.exception.repository.AlreadyExistException;
import com.mercadolibre.calculadorametroscuadrados.exception.repository.NotFoundException;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{

    private Map<String, DistrictDAO> districtDAOMap =  new HashMap<>();

    public DistrictRepositoryImpl() {
        loadDatabase();
    }

    private void loadDatabase(){
        districtDAOMap.put("Test", new DistrictDAO("Test", 220D ));
    }

    @Override
    public DistrictDAO findByName(String name) throws NotFoundException {
        if (districtDAOMap.containsKey(name)) {
            return districtDAOMap.get(name);
        }
        throw new NotFoundException();
    }

    @Override
    public void save(DistrictDAO district) {
        if (districtDAOMap.containsKey(district.getDistrict_name()))
            throw new AlreadyExistException();
        else
            districtDAOMap.put(district.getDistrict_name(), district);
    }

    @Override
    public boolean delete(String name) throws NotFoundException{
        if (districtDAOMap.containsKey(name)){
            districtDAOMap.remove(name);
            return Boolean.valueOf(Boolean.TRUE);
        }
        throw new NotFoundException();
    }

    @Override
    public DistrictDAO update(DistrictDAO district) {
        if (districtDAOMap.containsKey(district.getDistrict_name())) {
            districtDAOMap.put(district.getDistrict_name(), district);
            return district;
        }
        throw new NotFoundException();
    }
}
