package com.kjcelis.social_meli.service;

import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.SellerDTO;
import com.kjcelis.social_meli.dto.response.BuyerListPostResDTO;
import com.kjcelis.social_meli.dto.response.SellerListPpostResDTO;
import com.kjcelis.social_meli.exceptions.ErrorMessage;
import com.kjcelis.social_meli.exceptions.MeliAppException;

public interface SocialMeliService {
    void initInfo();

    String followSeller(Integer userId, Integer userIdToFollow);

    SellerDTO getCountUsersFV(Integer userId) throws ErrorMessage, MeliAppException;

    SellerDTO getListFollowS(Integer userId, String order);

    String saveNewPost(PostDTO postDTO);

    BuyerDTO getListFollowedB(Integer userId, String order);

    BuyerListPostResDTO getPostListB(Integer userId, String order);

    String unfollowSeller(Integer userId, Integer userIdToUnfollow);

    String saveNewPromoPost(PostDTO pPostDTO);

    SellerListPpostResDTO getCountPromoSeller(Integer userId);

    SellerListPpostResDTO getListProdSeller(Integer userId);
}
