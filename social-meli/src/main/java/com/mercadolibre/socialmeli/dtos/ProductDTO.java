package com.mercadolibre.socialmeli.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer product_id;
    @NotEmpty(message = "productName cannot be empty")
    private String productName;
    @NotEmpty(message = "type cannot be empty")
    private String type;
    @NotEmpty(message = "brand cannot be empty")
    private String brand;
    @NotEmpty(message = "color cannot be empty")
    private String color;
    private String notes;

}
