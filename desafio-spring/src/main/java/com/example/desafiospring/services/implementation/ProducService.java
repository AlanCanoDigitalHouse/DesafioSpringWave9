package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.ProductCreateDto;
import com.example.desafiospring.services.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProducService implements IProductService {
    @Override
    public ProductCreateDto getProductByPostId(Long idPost) {
        return null;
    }

    @Override
    public ProductCreateDto createProduct(ProductCreateDto productCreateDto) {
        return null;
    }
}
