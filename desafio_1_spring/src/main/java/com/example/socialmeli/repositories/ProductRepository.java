package com.example.socialmeli.repositories;

import com.example.socialmeli.exceptions.ProductNotFound;
import com.example.socialmeli.models.Product;

import java.util.HashMap;
import java.util.Objects;

public class ProductRepository implements IProductRepository{
    static HashMap<String, Product> products;
    static Integer counter;

    public ProductRepository(){
        products = new HashMap<>();
        counter=1;
    }

    public Product find(String productName) throws ProductNotFound {
        Product product = products.get(productName);
        if(Objects.isNull(product)){
            throw new ProductNotFound();
        }
        return products.get(productName);
    }

    public void add(Product product) {
        product.setProduct_id(counter);
        counter++;
        products.put(product.getProductName(),product);
    }
}
