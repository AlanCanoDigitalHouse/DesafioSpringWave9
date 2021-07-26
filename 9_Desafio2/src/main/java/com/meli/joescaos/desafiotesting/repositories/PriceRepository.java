package com.meli.joescaos.desafiotesting.repositories;

import com.meli.joescaos.desafiotesting.dto.PriceDTO;

import java.util.List;

public interface PriceRepository {
    PriceDTO districtExists(String districtName);
    List<PriceDTO> getDistrictList();
    double getDistrictPrice(String districtName);
}
