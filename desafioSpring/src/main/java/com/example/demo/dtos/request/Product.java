package com.example.demo.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Getter
@Setter
@NoArgsConstructor
@Validated
public class Product {

    private Integer product_id;

    @NotEmpty(message = "Product Name can't be empty")
    @NotNull(message = "Product Name is mandatory, it can't be null")
    private String productName;
    @NotEmpty(message = "Type can't be empty")
    @NotNull(message = "Type is mandatory, it can't be null")
    private String type;
    @NotEmpty(message = "Brand Name can't be empty")
    @NotNull(message = "Brand is mandatory, it can't be null")
    private String brand;
    @NotEmpty(message = "Color Name can't be empty")
    @NotNull(message = "Color is mandatory, it can't be null")
    private String color;
    @NotEmpty(message = "Notes Name can't be empty")
    @NotNull(message = "Notes is mandatory, it can't be null")
    private String notes;

}
