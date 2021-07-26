package com.mercadolibre.tucasitatasaciones.repositories;

import com.mercadolibre.tucasitatasaciones.entities.District;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;

public interface IDistrictRepository {

    District findByName(String location) throws DistrictNotFoundException;

}
