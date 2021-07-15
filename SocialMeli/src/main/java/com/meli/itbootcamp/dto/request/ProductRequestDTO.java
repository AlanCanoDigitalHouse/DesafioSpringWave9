package com.meli.itbootcamp.dto.request;

import com.meli.itbootcamp.model.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@Getter
@Setter

public class ProductRequestDTO {

    @NotNull(message = "Product's name can't be null")
    @NotBlank(message = "Product's name can't be empty")
    @Size(min = 2, max = 256, message = "name must be at least 2 characters long, and 256 at max")
    private String productName;

    @NotNull(message = "Product's type can't be null")
    @NotBlank(message = "Product's type can't be empty")
    @Size(min = 2, max = 2000, message = "type must be at least 2 characters long, and 256 at max")
    private String type;

    @NotNull(message = "Product's brand can't be null")
    @NotBlank(message = "Product's brand can't be empty")
    @Size(min = 2, max = 256, message = "brand must be at least 2 characters long, and 256 at max")
    private String brand;

    @NotNull(message = "Product's color can't be null")
    @NotBlank(message = "Product's color can't be empty")
    @Size(min = 2, max = 256, message = "color must be at least 2 characters long, and 256 at max")
    private String color;

    private String notes;

}
