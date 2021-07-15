package com.meli.itbootcamp.dto;

import com.meli.itbootcamp.model.Product;
import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO extends Product {

    public ProductDTO(Product detail) {
        super(detail.getProduct_id(), detail.getProductName(), detail.getType(), detail.getBrand(), detail.getColor()
                , detail.getNotes());
    }
}
