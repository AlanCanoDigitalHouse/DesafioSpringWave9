package com.mercadolibre.tucasita.repository;

import com.mercadolibre.tucasita.domain.District;

public interface DistrictRepository {
    District findByName(String name);
}
