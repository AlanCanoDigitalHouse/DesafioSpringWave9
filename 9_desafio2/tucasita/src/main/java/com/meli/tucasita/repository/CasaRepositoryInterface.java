package com.meli.tucasita.repository;

import com.meli.tucasita.dto.Distrito;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepositoryInterface {

    Distrito obtenerDistrito(String districtName) throws NoDistrictFoundException, DataBaseException;

}
