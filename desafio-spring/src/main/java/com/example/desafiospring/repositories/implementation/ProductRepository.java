package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.Product;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.repositories.IProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class ProductRepository implements IProductRepository {

    private final ObjectMapper mapper;

    public ProductRepository() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Product createProduct(Product product) throws IOException {
        List<Product> products = this.loadDataBase();
        product.setProduct_id(this.getLastId(products) + 1);
        products.add(product);
        this.writeDataBase(products);
        return product;
    }

    @Override
    public Product getProductByIdPost(Long idPost) throws IOException {
        Optional<Product> product = this.loadDataBase().stream()
                .filter(x -> x.getId_post().equals(idPost)).findFirst();
        return product.orElse(null);
    }

    private void writeDataBase(List<Product> products) throws IOException {
        mapper.writeValue(new File(ConstantEnum.JSON_PRODUCTS), products);
    }

    private List<Product> loadDataBase() throws IOException {
        return mapObject(Paths.get(ConstantEnum.JSON_PRODUCTS).toFile());
    }

    private List<Product> mapObject(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    private Long getLastId(List<Product> posts) {
        AtomicReference<Long> id = new AtomicReference<>(0L);
        posts.forEach(x -> {
            if (x.getProduct_id().compareTo(id.get()) > 0)
                id.set(x.getProduct_id());
        });
        return id.get();
    }

}
