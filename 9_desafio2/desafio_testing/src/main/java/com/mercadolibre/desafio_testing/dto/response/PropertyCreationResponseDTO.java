package com.mercadolibre.desafio_testing.dto.response;

import com.mercadolibre.desafio_testing.util.ConstantsUtils;
import lombok.*;

@Getter
@Setter
public class PropertyCreationResponseDTO extends GenericResponseDTO {
    public PropertyCreationResponseDTO() {
        super(ConstantsUtils.PROPERTY_CREATED);
    }
}
