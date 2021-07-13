package com.example.desafiospring.dtos.response;


import com.example.desafiospring.dtos.request.ProductRequestDto;
import com.example.desafiospring.utils.Factory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Integer product_id;
    private String productName;
    private String brand;
    private String color;
    private String notes;

    public ProductResponseDto(ProductRequestDto product) {
        this.product_id = Factory.generateId()  ;
        this.productName = product.getProductName();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.notes = product.getNotes();
    }
}
