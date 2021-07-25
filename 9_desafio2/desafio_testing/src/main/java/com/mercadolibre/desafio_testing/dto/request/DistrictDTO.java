package com.mercadolibre.desafio_testing.dto.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafio_testing.exception.*;
import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import lombok.*;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

@Getter
@Setter
public class DistrictDTO implements Validatable {
    private String district_name;
    private double district_price;

    public static DistrictDTO getDistrictDTO(String path) {
        DistrictDTO districtDTO = null;

        try {
            districtDTO = new ObjectMapper().readValue(
                    ResourceUtils.getFile(path),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return districtDTO;
    }

    public void validate() throws DistrictNotValidException {
        this.validateDistrictName(this.getDistrict_name());
        this.validatePrice(this.getDistrict_price());
    }

    public void validateDistrictName(String name)
            throws DistrictNotValidException {
        if (name == null || name.length() == 0) {
            throw new DistrictNotValidException(
                    ConstantsUtils.INVALID_DISTRICT_NAME);
        }

        if (name.length() > 45) {
            throw new DistrictNotValidException(
                    ConstantsUtils.DISTRICT_NAME_TOO_LONG);
        }
    }

    public void validatePrice(double price)
            throws DistrictNotValidException {
        if (price == 0) {
            throw new DistrictNotValidException(
                    ConstantsUtils.INVALID_PRICE);
        }

        if (price > 4000) {
            throw new DistrictNotValidException(
                    ConstantsUtils.PRICE_TOO_BIG);
        }
    }
}
