package com.example.desafio_spring.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ProductRequestDTO {
    private Integer product_id;
    @NotNull(message = "Debe contener un nombre de producto")
    @NotBlank(message = "Debe contener un nombre de producto")
    private String productName;
    private String type;
    @NotNull(message = "Debe contener la marca del producto")
    @NotBlank(message = "Debe contener la marca del producto")
    private String brand;
    private String color;
    private String notes;

    public ProductRequestDTO(Integer product_id, String productName, String type, String brand, String color, String notes) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }
}
