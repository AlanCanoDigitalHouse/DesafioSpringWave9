package com.mercadolibre.socialmeli.converter;

import com.mercadolibre.socialmeli.dto.ProductDto;


import com.mercadolibre.socialmeli.dto.request.ProductRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestToProductDtoConverter implements Converter<ProductRequest, ProductDto> {

    @Override
    public ProductDto convert(ProductRequest productRequest) {
        return ProductDto.builder()
                .productName(productRequest.getProductName())
                .productType(productRequest.getType())
                .productBrand(productRequest.getColor())
                .productNotes(productRequest.getNotes())
                .build();
    }
}
