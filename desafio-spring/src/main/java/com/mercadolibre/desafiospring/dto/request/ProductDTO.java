package com.mercadolibre.desafiospring.dto.request;

import com.mercadolibre.desafiospring.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDTO (Product product) {
        this.productName = product.getProductName();
        this.type = product.getType();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.notes = product.getNotes();
    }
}
