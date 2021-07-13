package com.example.desafiospring.repository.implementations;

import com.example.desafiospring.DTOS.requests.ProductRequestDTO;
import com.example.desafiospring.entities.FollowEntity;
import com.example.desafiospring.entities.ProductEntity;
import com.example.desafiospring.exceptions.general.DBNotAvailableException;
import com.example.desafiospring.repository.interfaces.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepositoryImpl implements ProductRepository {


    public static final java.lang.String PRODUCTS_DB_ROUTE = "classpath:static/products.json";

    private static final AtomicInteger autoIncrement = new AtomicInteger(getNextIDFromDB());

    @Override
    public Integer addProduct(ProductRequestDTO detail) {
        Integer productId = autoIncrement.getAndIncrement();
        addProductToDB(new ProductEntity(productId,
                detail.getProductName(),
                detail.getType(),
                detail.getBrand(),
                detail.getColor(),
                detail.getNotes()));
        return productId;
    }

    private void addProductToDB(ProductEntity product) {
        List<ProductEntity> dbElements = getDatabaseProducts();
        dbElements.add(product);
        overWriteProductsDB(dbElements);
    }

    private void overWriteProductsDB(List<ProductEntity> productsToWrite) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(PRODUCTS_DB_ROUTE), productsToWrite);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error writing to DB", e);
        }
    }

    private static List<ProductEntity> getDatabaseProducts() {
        File file;
        try {
            file = ResourceUtils.getFile(PRODUCTS_DB_ROUTE);
        } catch (Exception e) {
            throw new DBNotAvailableException("Error finding DB", e);
        }
        return loadFromJSON(file);
    }

    private static List<ProductEntity> loadFromJSON(File file) throws DBNotAvailableException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductEntity>> typeReference = new TypeReference<>(){};
        List<ProductEntity> products;
        try {
            products = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error reading DB", e);
        }
        return products;
    }

    private static int getNextIDFromDB() {
        int i = 1;
        if (getDatabaseProducts().size() > 0) {
            //noinspection OptionalGetWithoutIsPresent
            i = getDatabaseProducts().stream().max(Comparator.comparing(ProductEntity::getProductId)).get().getProductId() + 1;
        }
        return i;
    }
}
