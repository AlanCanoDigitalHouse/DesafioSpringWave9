package com.mercadolibre.desafio_testing.dto.response;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import lombok.*;

@Getter
@Setter
public class DistrictCreationReponseDTO extends GenericResponseDTO {
    public DistrictCreationReponseDTO() {
        super(ConstantsUtils.DISTRICT_CREATED);
    }
}
