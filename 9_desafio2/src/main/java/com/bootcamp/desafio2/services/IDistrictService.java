package com.bootcamp.desafio2.services;

import java.io.IOException;

public interface IDistrictService {

    boolean neighborhoodExist(String name, Double price) throws IOException;

}
