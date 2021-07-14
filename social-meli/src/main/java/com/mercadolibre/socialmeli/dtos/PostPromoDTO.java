package com.mercadolibre.socialmeli.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostPromoDTO extends PostDTO {

    @NotNull(message = "hasPromo cannot be null")
    @AssertTrue(message = "hasPromo should be true")
    private Boolean hasPromo = Boolean.TRUE;
    @DecimalMin(value = "0.0", inclusive = false, message = "discount should be between 0 and 1")
    @DecimalMax(value = "1.0", message = "discount should be between 0 and 1")
    private Double discount;

    public PostPromoDTO clonePostPromoDTO() {
        try {
            return (PostPromoDTO) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

}
