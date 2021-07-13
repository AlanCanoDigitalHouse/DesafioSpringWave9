package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.PostDTO;
import com.mercadolibre.socialmeli.dtos.SellerPostListDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;

public interface ProductService {

    void createPost(PostDTO postDTO) throws SellerNotFoundException;

    SellerPostListDTO twoWeeksSellerPosts(Integer userId, String order) throws BuyerNotFoundException;

}
