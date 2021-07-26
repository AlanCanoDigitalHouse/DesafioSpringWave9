package com.mercadolibre.calculadorametroscuadrados.repositories;

import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class DistrictsRepository {

    List<String> districts = new ArrayList<>();

    public void loadDistricts(){
        String d1 = "Centro";
        String d2 = "Chacarita";
        String d3 = "Valle Chico";
        String d4 = "Villa Reyes";
        String d5 = "Terrazas del Valle";

        districts.add(d1);
        districts.add(d2);
        districts.add(d3);
        districts.add(d4);
        districts.add(d5);
    }

    public Boolean getDistrict(String district_name){
        for (String s : districts){
           if (s.equals(district_name)){
               return true;
           }
        }
        throw new DistrictNotFoundException("/// El barrio no existe.");
    }
}
