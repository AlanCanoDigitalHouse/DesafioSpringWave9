package com.mercadolibre.social_meli.dto.response;

import com.mercadolibre.social_meli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Integer id_post;
    private String date;
    private Product detail;
    private Integer category;
    private Double price;

}
