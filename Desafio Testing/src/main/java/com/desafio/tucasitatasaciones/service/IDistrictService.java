package com.desafio.tucasitatasaciones.service;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.exception.DistrictNotFoundException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IDistrictService {
    PropertyResponseDTO propertyInfo(PropertyRequestDTO property) throws DistrictNotFoundException;
}
