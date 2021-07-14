package com.example.desafiospring.services.implementation;

import com.example.desafiospring.dtos.PostDto;
import com.example.desafiospring.dtos.ProductDto;
import com.example.desafiospring.entities.Product;
import com.example.desafiospring.repositories.IProductRepository;
import com.example.desafiospring.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService implements IProductService {

    private final ObjectMapper objectMapper;
    private final IProductRepository productRepository;

    public ProductService(ObjectMapper objectMapper, IProductRepository productRepository) {
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto getProductByIdPost(PostDto post) throws IOException {
        return this.objectMapper.convertValue(
                this.productRepository.getProductByIdPost(post.getId_post())
                , ProductDto.class);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, Long idPost) throws IOException {
        Product product = this.objectMapper.convertValue(productDto, Product.class);
        product.setId_post(idPost);
        product = this.productRepository.createProduct(product);
        return this.objectMapper.convertValue(product, ProductDto.class);
    }
}
