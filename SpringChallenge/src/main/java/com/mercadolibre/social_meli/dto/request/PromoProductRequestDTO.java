package com.mercadolibre.social_meli.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class PromoProductRequestDTO extends ProductRequestDTO {

    @NotNull(message = "Promo posts need a hasPromo field")
    private Boolean hasPromo;

    @NotNull(message = "Promo posts need a discount")
    @DecimalMin(value = "0.1", message = "Discount value can't be lower than 0.1")
    private Double discount;

}
