package com.socialMeli.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PromoPostInfoToCreateDTO extends PostInfoToCreateDTO {
    @NotNull(message = "has promo cant be null")
    private Boolean hasPromo;
    @NotNull(message = "discount cant be null")
    @DecimalMin(value = "0", message = "discount cant be minor than 0%")
    @DecimalMax(value = "1", message = "discount cant be major than 100%")
    private Double discount;
}
