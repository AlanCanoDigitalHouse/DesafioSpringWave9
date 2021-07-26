package com.mercadolibre.TuCasitaTasacionesDesafioTesting.repositories;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity.District;

import java.util.ArrayList;
import java.util.Optional;


public interface ITuCasitaTasacionesRepository {

    Optional<District> findPriceByLocation(String location);

    ArrayList<District> loadDatabase();

}
