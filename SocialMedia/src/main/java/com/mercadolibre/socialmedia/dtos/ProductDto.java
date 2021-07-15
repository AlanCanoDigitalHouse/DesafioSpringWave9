package com.mercadolibre.socialmedia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "This field cant be null.")
    @NotBlank(message = "This field cant be blank.")
    private String productName;

    @NotNull(message = "This field cant be null.")
    @NotBlank(message = "This field cant be blank.")
    private String type;

    @NotNull(message = "This field cant be null.")
    @NotBlank(message = "This field cant be blank.")
    private String brand;

    @NotNull(message = "This field cant be null.")
    @NotBlank(message = "This field cant be blank.")
    private String color;

    // Optional: this field can exist or not
    private String notes;
}
