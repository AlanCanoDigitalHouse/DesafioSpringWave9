package com.mercadolibre.desafio_testing.dto.response;

import lombok.*;

@Getter
@Setter
public class PropertyPriceResponseDTO {
    private double price;

    public PropertyPriceResponseDTO(double price) {
        this.price = price;
    }
}
