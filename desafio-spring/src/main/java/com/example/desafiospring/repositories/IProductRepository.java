package com.example.desafiospring.repositories;

import com.example.desafiospring.entities.Product;

public interface IProductRepository {

    Product createProduct(Product product);

    Product getProductByIdPost(Long idPost);

}
