package com.example.desafiotesting.services;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;

public interface IDistrictService {
    DistrictDTO getDistrictByName(String name) throws DistrictException, FileException;
}
