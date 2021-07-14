package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.models.Product;

public interface IProductRepository extends IRepository<Product, Integer, ProductException> {
    Integer addProduct(Product product);
}
