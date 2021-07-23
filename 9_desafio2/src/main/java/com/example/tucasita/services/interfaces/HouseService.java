package com.example.tucasita.services.interfaces;

import com.example.tucasita.DTO.request.HouseRequestDTO;
import com.example.tucasita.DTO.response.HouseResponseDTO;

public interface HouseService {
    HouseResponseDTO calculateForHouse (HouseRequestDTO houseRequestDTO);
}
