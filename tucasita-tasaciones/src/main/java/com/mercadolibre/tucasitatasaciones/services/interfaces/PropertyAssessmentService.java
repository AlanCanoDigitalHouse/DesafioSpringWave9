package com.mercadolibre.tucasitatasaciones.services.interfaces;

import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;

public interface PropertyAssessmentService {

    AssessmentDTO calcDimensionM2(PropertyDTO prop) throws DistrictNotFoundException;

    AssessmentDTO calcPrice(PropertyDTO prop) throws DistrictNotFoundException;

    AssessmentDTO getBiggestRoom(PropertyDTO prop) throws DistrictNotFoundException;

    AssessmentDTO getRoomsDimensions(PropertyDTO prop) throws DistrictNotFoundException;

}
