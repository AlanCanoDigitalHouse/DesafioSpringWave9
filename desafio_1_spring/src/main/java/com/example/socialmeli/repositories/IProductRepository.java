package com.example.socialmeli.repositories;

import com.example.socialmeli.exceptions.ProductNotFound;
import com.example.socialmeli.exceptions.UserNotFound;
import com.example.socialmeli.models.Product;
import com.example.socialmeli.models.User;

public interface IProductRepository {

    Product find(String productName) throws ProductNotFound;

    void add(Product product);
}
