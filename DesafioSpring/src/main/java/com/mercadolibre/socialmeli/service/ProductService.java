package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.ProductDto;
import com.mercadolibre.socialmeli.dto.request.NewPostRequest;
import com.mercadolibre.socialmeli.entity.Product;
import com.mercadolibre.socialmeli.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ConversionService conversionService;

    @Autowired
    public ProductService(final ConversionService conversionService, IProductRepository productRepository) {
        this.conversionService = conversionService;
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(NewPostRequest post) {
        final ProductDto productDto = conversionService.convert(post.getDetail(), ProductDto.class);
        Product p = new Product(productDto);
        return productRepository.save(p);
    }

    public Product listProductsFollowed(Integer id) {
        return productRepository.getById(id);
    }

}
