package com.example.socialmeli.repositories;

import com.example.socialmeli.exceptions.ProductNotFound;
import com.example.socialmeli.models.Product;

public interface IProductRepository {

    Product find(String productName) throws ProductNotFound;

    void add(Product product);
}
