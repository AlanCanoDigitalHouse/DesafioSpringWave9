package com.example.tucasitatasaciones.services;

import com.example.tucasitatasaciones.dtos.DistrictDTO;
import com.example.tucasitatasaciones.dtos.EnviromentDTO;
import com.example.tucasitatasaciones.dtos.PropertyDTO;
import com.example.tucasitatasaciones.dtos.response.PropertyResponseDTO;
import com.example.tucasitatasaciones.exceptions.DistrictException;

import java.util.List;

public interface PropertyService {
    PropertyResponseDTO calculateProperty(PropertyDTO property) throws DistrictException;
}
