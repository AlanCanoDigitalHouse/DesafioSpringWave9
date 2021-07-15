package com.kjcelis.social_meli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
