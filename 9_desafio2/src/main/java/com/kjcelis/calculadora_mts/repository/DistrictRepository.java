package com.kjcelis.calculadora_mts.repository;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import com.kjcelis.calculadora_mts.exceptions.NotFoundDistricException;


public interface DistrictRepository {
    DistrictDTO findDistrictRepo(String districtName, double districtPrice) throws NotFoundDistricException;
}
