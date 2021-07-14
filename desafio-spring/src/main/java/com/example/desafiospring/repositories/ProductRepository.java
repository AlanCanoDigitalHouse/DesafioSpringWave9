package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.ProductException;
import com.example.desafiospring.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {

    @Override
    public Product findById(Long id) throws ProductException {
        List<Product> products = this.loadDB();
        Optional<Product> result = products.stream().filter(p -> p.getProductId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new ProductException(ProductException.PRODUCT_NOT_EXISTS + id);
    }

    @Override
    public Collection<Product> findByIds(Collection<Long> ids) {
        List<Product> products = this.loadDB();
        return products.stream().filter(s -> ids.contains(s.getProductId())).collect(Collectors.toList());
    }

    private List<Product> loadDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/products.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<Product> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Product>> typeReference = new TypeReference<>() {
        };
        List<Product> products = null;
        try {
            products = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private List<Product> saveToFile(List<Product> products) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/products.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(products);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Long addProduct(Product product) {
        List<Product> products = this.loadDB();
        Optional<Long> maxIdProduct = products.stream().max(Comparator.comparing(Product::getProductId)).map(p -> p.getProductId());
        Long idProduct;
        if (maxIdProduct.isEmpty())
            idProduct = 1L;
        else
            idProduct = maxIdProduct.get() + 1;
        product.setProductId(idProduct);
        products.add(product);
        this.saveToFile(products);
        return idProduct;
    }
}
