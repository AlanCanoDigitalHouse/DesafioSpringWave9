package com.mercadolibre.socialmeli.services.interfaces;

import com.mercadolibre.socialmeli.dtos.*;
import com.mercadolibre.socialmeli.dtos.resp.FollowedPostsDTO;
import com.mercadolibre.socialmeli.dtos.resp.SellerPostsPromoDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;


public interface ProductService {

    void createPost(PostDTO postDTO) throws SellerNotFoundException;

    FollowedPostsDTO followedSellersPostsLastTwoWeeks(Integer userId, String order) throws BuyerNotFoundException;

    SellerPostsPromoDTO getPromoProductsCount(Integer selleId) throws SellerNotFoundException;

    SellerPostsPromoDTO getPromoPosts(Integer sellerId) throws SellerNotFoundException;

}
