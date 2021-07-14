package com.example.socialmeli.domains;

import com.example.socialmeli.dtos.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Product {

    private static AtomicInteger indexProduct = new AtomicInteger();

    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public Product(ProductRequestDTO dto){
        this.product_id = indexProduct.incrementAndGet();
        this.productName = dto.getProductName();
        this.type = dto.getType();
        this.brand = dto.getBrand();
        this.color = dto.getColor();
        this.notes = dto.getNotes();
    }

}
