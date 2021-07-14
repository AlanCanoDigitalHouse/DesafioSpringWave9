package com.example.socialmeli.handlers;

import com.example.socialmeli.dtos.requests.RequestProductDto;
import com.example.socialmeli.models.Product;

public class ProductHandler {
    public static Product dtoToProducto(RequestProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setBrand(productDto.getBrand());
        product.setColor(productDto.getColor());
        product.setNotes(productDto.getNotes());
        product.setType(productDto.getType());
        return product;
    }
}
