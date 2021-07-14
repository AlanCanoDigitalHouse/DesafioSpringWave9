package com.socialmeli.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.socialmeli.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDTO {

    @JsonProperty("product_id")
    private Integer productId;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductResponseDTO(ProductDTO product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.type = product.getType();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.notes = product.getNotes();
    }
}