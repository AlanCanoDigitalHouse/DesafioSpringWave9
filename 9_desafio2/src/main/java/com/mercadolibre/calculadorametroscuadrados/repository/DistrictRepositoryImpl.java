package com.mercadolibre.calculadorametroscuadrados.repository;

import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class DistrictRepositoryImpl {

    private HashMap<String, Double> districtPrice;

    public DistrictRepositoryImpl(){
        this.districtPrice = new HashMap<>();
        this.districtPrice.put("Santiago", 3000.0);
        this.districtPrice.put("Las Condes", 4000.0);
        this.districtPrice.put("Providencia", 3500.0);
    }

    public Double findDistrictPrice(String name) throws DistrictNotFound {

        return Optional.ofNullable(this.districtPrice.get(name)).orElseThrow(()->new DistrictNotFound());
    }


}
