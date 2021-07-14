package com.mercadolibre.desafio1.repositories;

import com.mercadolibre.desafio1.dto.response.ProductResponseDTO;
import com.mercadolibre.desafio1.repositories.interfaces.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {
    private Map<Integer, ProductResponseDTO> products = new HashMap<>();
    private static final AtomicInteger productIdCount = new AtomicInteger(0);

    @Override
    public ProductResponseDTO getProductById(Integer productId) {
        ProductResponseDTO result = null;
        if(this.products.containsKey(productId)){
            result = this.products.get(productId);
        }
        return result;
    }

    @Override
    public ProductResponseDTO addProduct(String productName, String type, String brand, String color, String notes) {
        ProductResponseDTO newProduct = new ProductResponseDTO(productIdCount.addAndGet(1),productName,type,brand,color,notes);
        products.put(newProduct.getProduct_id(),newProduct);

        return newProduct;
    }


}
