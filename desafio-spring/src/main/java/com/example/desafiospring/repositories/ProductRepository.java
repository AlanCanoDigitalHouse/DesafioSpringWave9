package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    public static final String FILENAME = "products.json";

    @Override
    public Product findById(Long id) throws ProductException {
        List<Product> products = this.loadDB("products.json", Product.class);
        Optional<Product> result = products.stream().filter(p -> p.getProductId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new ProductException(ProductException.PRODUCT_NOT_EXISTS + id);
    }

    @Override
    public Collection<Product> findByIds(Collection<Long> ids) {
        List<Product> products = this.loadDB(FILENAME, Product.class);
        return products.stream().filter(s -> ids.contains(s.getProductId())).collect(Collectors.toList());
    }

    @Override
    public Long addProduct(Product product) {
        List<Product> products = this.loadDB("products.json", Product.class);
        Optional<Long> maxIdProduct = products.stream().max(Comparator.comparing(Product::getProductId)).map(p -> p.getProductId());
        Long idProduct;
        if (maxIdProduct.isEmpty())
            idProduct = 1L;
        else
            idProduct = maxIdProduct.get() + 1;
        product.setProductId(idProduct);
        products.add(product);
        this.saveToFile(FILENAME, products);
        return idProduct;
    }
}
