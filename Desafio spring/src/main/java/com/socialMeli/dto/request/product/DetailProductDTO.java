package com.socialMeli.dto.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class DetailProductDTO {
    @NotNull(message = "Product name can't be null")
    private String productName;
    @NotNull(message = "Type can't be null")
    private String type;
    @NotNull(message = "Brand can't be null")
    private String brand;
    @NotNull(message = "Color name can't be null")
    private String color;
    @NotNull(message = "Notes can't be null")
    private String notes;

}
