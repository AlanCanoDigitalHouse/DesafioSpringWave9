package com.example.socialmeli.dtos.requests;

import lombok.Data;

@Data
public class RequestProductDto {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
