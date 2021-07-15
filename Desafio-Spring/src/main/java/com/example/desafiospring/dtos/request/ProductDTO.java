package com.example.desafiospring.dtos.request;

import com.example.desafiospring.dtos.general.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotNull(message ="Value is require")
    @NotBlank
    @Size(min = 3, max = 50)
    private String productName;
    @NotNull(message ="Value is require")
    @NotBlank
    @Size(min = 3, max = 50)
    private String type;
    @NotNull(message ="Value is require")
    @NotBlank
    @Size(min = 3, max = 50)
    private String brand;
    @NotNull(message ="Value is require")
    @NotBlank
    @Size(min = 3, max = 50)
    private String color;
    @NotNull(message ="Value is require")
    @NotBlank
    @Size(min = 3, max = 50)
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
