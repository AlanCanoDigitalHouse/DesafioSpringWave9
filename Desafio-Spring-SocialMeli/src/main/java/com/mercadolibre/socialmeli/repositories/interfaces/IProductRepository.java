package com.mercadolibre.socialmeli.repositories.interfaces;

import com.mercadolibre.socialmeli.dtos.posts.requests.ProductRequestDTO;
import com.mercadolibre.socialmeli.dtos.posts.responses.ProductResponseDTO;

public interface IProductRepository {
    ProductResponseDTO addNew(ProductRequestDTO detail);
}
