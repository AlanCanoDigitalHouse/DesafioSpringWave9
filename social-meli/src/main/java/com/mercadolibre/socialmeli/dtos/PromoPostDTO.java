package com.mercadolibre.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PromoPostDTO extends PostDTO {

    private Boolean hasPromo = Boolean.TRUE;
    @DecimalMin(value = "0.0", inclusive = false, message = "discount should be between 0 and 1")
    @DecimalMax(value = "1.0", message = "discount should be between 0 and 1")
    private Double discount;

}
