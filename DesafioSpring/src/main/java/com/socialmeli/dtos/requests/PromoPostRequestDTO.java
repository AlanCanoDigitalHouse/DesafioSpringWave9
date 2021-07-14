package com.socialmeli.dtos.requests;

import com.socialmeli.validations.ValidateDiscount;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ValidateDiscount(field = "discount", message = "discount only be null if hasPromo is false")
public class PromoPostRequestDTO extends PostRequestDTO {

    @NotNull(message = "hasPromo must not be null")
    private Boolean hasPromo;

    @DecimalMin(value = "0.01", message = "discount must be greater/equals than 0.01")
    @DecimalMax(value = "1.0", message = "discount must be lower than 1")
    private Double discount;

    public PromoPostRequestDTO(Integer userId, String date, ProductRequestDTO detail, Integer category, Double price,
                               Boolean hasPromo, Double discount) {
        super(userId, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
}
