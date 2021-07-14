package com.mercadolibre.social_meli.dto.request;

import com.mercadolibre.social_meli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Validated
public class ProductRequestDTO {

    @NotNull(message = "A userId must be provided")
    private Integer userId;

    @NotNull(message = "A publication date must be provided")
    @NotBlank(message = "A publication date must be provided")
    private String date;

    @Valid
    private Product detail;

    @NotNull(message = "Post needs a category")
    private Integer category;

    @NotNull(message = "Publication needs a selling price to be valid")
    @Min(value = 1, message = "Product price can't be below 1")
    private Double price;

}
