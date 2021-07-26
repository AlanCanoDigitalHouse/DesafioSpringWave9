package com.mercadolibre.tucasitatasaciones.services;

import com.mercadolibre.tucasitatasaciones.dtos.request.HouseDTO;
import com.mercadolibre.tucasitatasaciones.dtos.response.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exceptions.DistrictNotFoundException;

public interface IHouseService {
    AssessmentDTO assessmentSquareMeters(HouseDTO house) throws DistrictNotFoundException;

    AssessmentDTO assessmentSquareMetersEachRoom(HouseDTO house) throws DistrictNotFoundException;

    AssessmentDTO assessmentBiggestRoom(HouseDTO house) throws DistrictNotFoundException;

    AssessmentDTO assessmentPrice(HouseDTO house) throws DistrictNotFoundException;
}
