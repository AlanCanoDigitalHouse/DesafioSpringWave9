package com.meli.itbootcamp.dto.request;

import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PromoRequestDTO extends PostRequestDTO {
    private Boolean hasPromo;

    @NotNull(message = "discount can't be null")
    @DecimalMin(message = "price must be greater than 0.01", value = "0.01")
    @DecimalMax(message = "price must be lower than 1.00", value = "1.0")
    private Double discount;

}
