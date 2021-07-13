package com.example.desafiospring.dtos.request;

import com.example.desafiospring.dtos.general.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//agregar validaciones
public class ProductDTO {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product parseToProduct(){
        Product product = new Product();
        product.setProductName(this.productName);
        product.setNotes(this.notes);
        product.setColor(this.color);
        product.setBrand(this.brand);
        product.setType(this.type);
        product.setProductId(null);
        return product;
    }

}
