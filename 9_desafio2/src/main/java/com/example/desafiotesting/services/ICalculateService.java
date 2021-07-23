package com.example.desafiotesting.services;

import com.example.desafiotesting.dto.PropertyDTO;
import com.example.desafiotesting.dto.response.PropertyResponseDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;

public interface ICalculateService {
    PropertyResponseDTO calculate(PropertyDTO property) throws FileException, DistrictException;
}
