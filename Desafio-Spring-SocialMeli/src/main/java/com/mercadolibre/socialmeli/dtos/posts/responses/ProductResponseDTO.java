package com.mercadolibre.socialmeli.dtos.posts.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {

    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

}
