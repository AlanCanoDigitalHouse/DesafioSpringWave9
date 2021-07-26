package com.example._9desafio2.repositories;

import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;

public interface IPriceRepository {
     DistrictDTO findPriceDistrict(String location) throws DistrictNotFoundException;
}
