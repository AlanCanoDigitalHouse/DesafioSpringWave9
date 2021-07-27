package com.mercadolibre.tucasita.repository;

import com.mercadolibre.tucasita.dto.DistrictDTO;
import com.mercadolibre.tucasita.exception.DistrictNotFoundException;
import com.mercadolibre.tucasita.repositories.PriceRepositoryImpl;
import com.mercadolibre.tucasita.utils.TestGeneratorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceRepositoryTest {

    PriceRepositoryImpl priceRepository;

    @BeforeEach
    void init(){
        this.priceRepository = new PriceRepositoryImpl();
    }

    @Test
    @DisplayName("Given a district, when doesn't exists, exception is thrown")
    void findPriceLocationNotFoundTest() {
        assertThrows(DistrictNotFoundException.class, () -> {
            priceRepository.findPriceLocation(TestGeneratorUtils.caballito.getDistrict_name());
        });
    }
    @Test
    @DisplayName("Given a district, when exists, price is ok")
    void findPriceLocationTest() {
            DistrictDTO districtDTO =
                    priceRepository.findPriceLocation(TestGeneratorUtils.palermo.getDistrict_name());

            assertEquals(1000D,districtDTO.getDistrict_price());
    }
}

