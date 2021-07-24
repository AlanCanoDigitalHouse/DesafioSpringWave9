package com.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSocial {

    private Integer id;
    private String name;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
