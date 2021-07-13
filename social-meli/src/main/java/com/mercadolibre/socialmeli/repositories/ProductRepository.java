package com.mercadolibre.socialmeli.repositories;

import com.mercadolibre.socialmeli.dto.Publication;

import java.util.List;

public interface ProductRepository {
    List<Publication> findAllByUser(int userId);

    Publication addPublication(Publication publication);

    List<Publication> findAllPromoByUser(int userId);

    Publication addProductPromotion(Publication promo);

    List<Publication> findAll();
}
