package com.bootcamp.desafio2.repositories;


import com.bootcamp.desafio2.entities.District;

import java.io.IOException;

public interface IDistrictRepository {

    boolean districtExist(String district_name, Double district_price) throws IOException;

}
