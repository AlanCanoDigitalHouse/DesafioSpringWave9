package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.Product;
import com.example.desafiospring.repositories.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    private static List<Product> products;
    private static Long id;

    public ProductRepository() {
        products = new ArrayList<>();
        id = 0L;
    }

    @Override
    public Product createProduct(Product product) {
        product.setProduct_id(++id);
        products.add(product);
        return product;
    }

    @Override
    public Product getProductByIdPost(Long idPost) {
        Optional<Product> product = products.stream().filter(x -> x.getId_post().equals(idPost)).findFirst();
        if (product.isPresent())
            return product.get();
        return null;
    }

}
