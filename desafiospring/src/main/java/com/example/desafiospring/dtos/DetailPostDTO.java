package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class DetailPostDTO {

    private Integer productID;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
