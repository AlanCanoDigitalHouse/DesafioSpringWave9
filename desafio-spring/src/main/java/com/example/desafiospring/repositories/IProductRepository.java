package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.models.Product;

public interface IProductRepository extends IRepository<Product, Long, ProductException> {
    Long addProduct(Product product);
}
