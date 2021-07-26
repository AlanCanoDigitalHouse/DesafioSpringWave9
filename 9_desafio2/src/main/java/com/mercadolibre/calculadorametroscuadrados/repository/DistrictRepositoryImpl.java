package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
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
        districtDAOMap.put("Cheap", new DistrictDAO("Cheap", 100D ));
        districtDAOMap.put("Medium", new DistrictDAO("Medium", 2000D ));
        districtDAOMap.put("Expensive", new DistrictDAO("Expensive", 4000D ));
        districtDAOMap.put("North", new DistrictDAO("North", 230D ));
        districtDAOMap.put("South", new DistrictDAO("South", 320D ));
        districtDAOMap.put("East", new DistrictDAO("East", 4000D ));
        districtDAOMap.put("West", new DistrictDAO("West", 2100D ));
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
            return true;
        }
        throw new NotFoundException();
    }

    @Override
    public DistrictDAO update(DistrictDAO district) throws NotFoundException {
        if (districtDAOMap.containsKey(district.getDistrict_name())) {
            districtDAOMap.put(district.getDistrict_name(), district);
            return district;
        }
        throw new NotFoundException();
    }
}
