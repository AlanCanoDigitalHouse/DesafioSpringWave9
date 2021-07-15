package com.kjcelis.social_meli.repositories.buyer;

import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.SellerDTO;

import java.util.Optional;

public interface BuyerRepository {

    void initListBuyer();

    Optional<BuyerDTO> findBuyerById(Integer linkId);

    void saveFollowS(Integer userId, Optional<SellerDTO> sellerDTO);

    void unfollowSelle(BuyerDTO buyerDTO, Integer userIdToUnfollow);
}
