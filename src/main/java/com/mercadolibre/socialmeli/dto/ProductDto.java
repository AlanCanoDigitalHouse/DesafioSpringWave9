package com.mercadolibre.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productName;
    private String productType;
    private String productBrand;
    private String productColor;
    private String productNotes;


}

