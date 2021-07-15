package com.meli.joescaos.socialmeli.socialmeli.services.Impl;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.BuyerDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerFollowersCountDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerFollowersDto;
import com.meli.joescaos.socialmeli.socialmeli.exceptions.UserNotFoundException;
import com.meli.joescaos.socialmeli.socialmeli.models.Buyer;
import com.meli.joescaos.socialmeli.socialmeli.models.Seller;
import com.meli.joescaos.socialmeli.socialmeli.repositories.SellerRepository;
import com.meli.joescaos.socialmeli.socialmeli.services.SellerService;
import com.meli.joescaos.socialmeli.socialmeli.utils.OrderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    // Attributes
    private SellerRepository sellerRepository;

    // Constructor
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    /**
     *
     * @param userId
     * @return SellerCountDto
     */
    @Override
    public SellerFollowersCountDto getFollowersCount(int userId) {
        Seller seller = sellerRepository.findById(userId);
        return new SellerFollowersCountDto(seller.getUserId(), seller.getUserName(), seller.getFollowers().size());
    }

    /**
     *
     * @param userId
     * @param order
     * @return SellerFollowersDto
     */
    @Override
    public SellerFollowersDto getFollowersList(int userId, String order) {
        Seller seller = sellerRepository.findById(userId);
        List<Buyer> buyers = seller.getFollowers();
        List<BuyerDto> buyersDtoList = new ArrayList<>();
        SellerFollowersDto sellerFollowersList = new SellerFollowersDto();
        sellerFollowersList.setUserId(seller.getUserId());
        sellerFollowersList.setUserName(seller.getUserName());
        buyers.forEach(buyer -> {
            BuyerDto buyerDto = new BuyerDto();
            buyerDto.setUserId(buyer.getUserId());
            buyerDto.setUserName(buyer.getUserName());
            buyersDtoList.add(buyerDto);
        });
        sellerFollowersList.setFollowers(buyersDtoList);
        if (order != null)
         OrderUtils.orderFollowers(sellerFollowersList.getFollowers(), order);

        return sellerFollowersList;
    }
}
