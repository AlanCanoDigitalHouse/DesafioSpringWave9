package com.mercadolibre.calculadorametroscuadrados.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.calculadorametroscuadrados.entities.District;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DataBaseNotAvaibleException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    public MyDataSource dataSource;

    public DistrictRepositoryImpl(MyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public double getPriceByDistrict(String districtName) {
       double price= getAllDistricts()
               .stream()
               .filter(district -> district.getDistrict_name().equalsIgnoreCase(districtName))
               .map(district -> district.getDistrict_price())
               .findFirst().orElseThrow(DistrictNotFoundException::new);
       return price;
    }

    @Override
    public List<District> getAllDistricts() {
        List<District> districts = null;
        try {
            districts = dataSource.loadDB();
        } catch (IOException e) {
            throw new DataBaseNotAvaibleException();
        }
        return districts;
    }

    /*private List<District> loadDB() throws IOException {
        File file = null;
        file = ResourceUtils.getFile("classpath:static/districts.json");

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeReference = new TypeReference<List<District>>() {
        };

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<District> districts;
        districts = objectMapper.readValue(file, typeReference);

        return districts;
    }*/

}
