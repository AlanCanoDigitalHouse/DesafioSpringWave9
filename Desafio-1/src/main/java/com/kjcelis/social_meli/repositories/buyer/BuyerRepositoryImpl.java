package com.kjcelis.social_meli.repositories.buyer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.SellerDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Repository
public class BuyerRepositoryImpl implements BuyerRepository {

    private final ArrayList<BuyerDTO> databaseBuyer = new ArrayList<>();


    public void saveFollowS(Integer userId, Optional<SellerDTO> sellerDTO) {
        if (findBuyerById(userId).isPresent()) {
            for (BuyerDTO b : databaseBuyer) {
                if (sellerDTO.isPresent() && b.getUserId().equals(userId)) {
                    SellerDTO sellerDTO1 = new SellerDTO(sellerDTO.get().getUserId(), sellerDTO.get().getUserName());
                    b.getFollowed().add(sellerDTO1);
                }
            }
        }
    }

    @Override
    public void unfollowSelle(BuyerDTO buyerDTO, Integer userIdToUnfollow) {
        for (int i = 0; i < buyerDTO.getFollowed().size(); i++) {
            if (buyerDTO.getFollowed().get(i).getUserId().equals(userIdToUnfollow)) {
                buyerDTO.getFollowed().remove(i);
            }
        }


    }

    public Optional<BuyerDTO> findBuyerById(Integer userId) {
        BuyerDTO buyerDTO = null;
        for (BuyerDTO b : databaseBuyer) {
            if (b.getUserId().equals(userId)) {
                buyerDTO = b;
            }
        }
        return Optional.ofNullable(buyerDTO);


    }

    public void initListBuyer() {
        List<BuyerDTO> buyerDTOS;
        buyerDTOS = loadDatabase();
        for (BuyerDTO b : buyerDTOS) {
            databaseBuyer.add(new BuyerDTO(b.getUserId(), b.getUserName(), new ArrayList<>()));
        }

    }

    private List<BuyerDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/buyer.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }

    private List<BuyerDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<BuyerDTO>> typeReference = new TypeReference<>() {
        };
        List<BuyerDTO> buyerDTOS = null;
        try {
            buyerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buyerDTOS;
    }
}
