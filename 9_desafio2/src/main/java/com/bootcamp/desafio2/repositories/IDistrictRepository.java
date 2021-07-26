package com.bootcamp.desafio2.repositories;

import java.io.IOException;

public interface IDistrictRepository {

    boolean districtExist(String districtName, Double districtPrice) throws IOException;

}
