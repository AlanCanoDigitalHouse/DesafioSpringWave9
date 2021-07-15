package com.mercadolibre.socialmeli.repositories.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dtos.posts.requests.ProductRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.ProductResponseDTO;
import com.mercadolibre.socialmeli.repositories.interfaces.IProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("prodsRepo")
public class ProductRepositoryImpl implements IProductRepository {

    private static List<ProductResponseDTO> productsList;
    private static final AtomicInteger idGenerated = new AtomicInteger(0);

    public ProductRepositoryImpl() {
        productsList = loadDatabase();
    }

    @Override
    public ProductResponseDTO addNew(ProductRequestDTO detail) {
        ProductResponseDTO newProduct = new ProductResponseDTO(
                idGenerated.incrementAndGet(),
                detail.getProductName(),
                detail.getType(),
                detail.getBrand(),
                detail.getColor(),
                detail.getNotes());
        productsList.add(newProduct);
        return newProduct;
    }

    private List<ProductResponseDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/products.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapToObject(file);
    }

    private List<ProductResponseDTO> mapToObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductResponseDTO>> typeReference = new TypeReference<>() {
        };
        List<ProductResponseDTO> productsDB = null;
        try {
            productsDB = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productsDB;
    }
}
