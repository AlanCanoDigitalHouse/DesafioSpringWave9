package com.example.DesafioSpring.servicies;

import com.example.DesafioSpring.dtos.BuyerResponseDTO;
import com.example.DesafioSpring.dtos.BuyerResponseMsgDTO;
import com.example.DesafioSpring.dtos.SellerResponseDTO;
import com.example.DesafioSpring.dtos.SellerResponseSingleDTO;

public interface IUserService {

    BuyerResponseMsgDTO followSeller(Integer userId, Integer userIdFollow);
    SellerResponseSingleDTO countFollowersSeller(Integer userId);
    SellerResponseDTO listFollowersSeller(Integer userId);
    BuyerResponseDTO listFollowedSeller(Integer userId);
    BuyerResponseMsgDTO unfollow(Integer userId, Integer userIdToUnfollow);
    SellerResponseDTO listFollowersSellerAscDes(Integer userId, String mode);
    BuyerResponseDTO listFollowedSellerAscDes(Integer userId, String mode);

}
