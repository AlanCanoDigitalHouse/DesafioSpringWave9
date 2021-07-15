package com.kjcelis.social_meli.repositories.seller;

import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.SellerDTO;

import java.util.Optional;

public interface SellerRepository {

    void initListSeller();

    Optional<SellerDTO> findSellerById(Integer UserId);

    void saveFollowB(Integer userId, Optional<BuyerDTO> buyerDTO);

    void savePost(Optional<SellerDTO> sellerById, PostDTO postDTO);

    void unfollowedBuye(SellerDTO sellerDTO, Integer userId);

    void savePromoPost(Optional<SellerDTO> sellerById, PostDTO pPostDTO);
}
