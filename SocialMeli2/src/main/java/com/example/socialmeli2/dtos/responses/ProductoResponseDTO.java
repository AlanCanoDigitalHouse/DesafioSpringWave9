package com.example.socialmeli2.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {

    private Integer product_id;
    private String name;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
