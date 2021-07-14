package com.example.desafiospring.repository.interfaces;

import com.example.desafiospring.DTOS.requests.ProductRequestDTO;
import com.example.desafiospring.DTOS.responses.ProductResponseDTO;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    Integer addProduct(ProductRequestDTO detail);

    ProductResponseDTO getProductResponseDTO(Integer productId);

    List<ProductResponseDTO> getProductResponseDTOSByID(Set<Integer> productIds);

    String getProductNameByID(Integer productId);
}
