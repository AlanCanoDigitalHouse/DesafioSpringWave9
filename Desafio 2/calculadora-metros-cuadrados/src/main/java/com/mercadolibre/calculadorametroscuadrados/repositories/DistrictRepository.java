package com.mercadolibre.calculadorametroscuadrados.repositories;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DistrictRepository implements IDistrictRepository{

    public List<String> districtList = new ArrayList<>();
    private static DistrictRepository instance= null;


    public static DistrictRepository getInstance(){
        if (instance==null){
            instance= new DistrictRepository();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void initialize(){
        this.districtList.add("Malvin");
        this.districtList.add("Carrasco");
        this.districtList.add("Pocitos");
        this.districtList.add("Punta carretas");
        this.districtList.add("Punta gorda");
        this.districtList.add("Blanqueada");

    }


    @Override
    public boolean existDistrict(String district) {
        return districtList.contains(district);
    }

}
