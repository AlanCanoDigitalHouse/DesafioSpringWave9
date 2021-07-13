package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.SellerPostListDTO;
import com.mercadolibre.socialmeli.exceptions.RecordNotFoundException;

public interface ProductService {

    void createPost(PostDTO postDTO);

    SellerPostListDTO twoWeeksSellerPosts(Integer userId, String order) throws RecordNotFoundException;

}
