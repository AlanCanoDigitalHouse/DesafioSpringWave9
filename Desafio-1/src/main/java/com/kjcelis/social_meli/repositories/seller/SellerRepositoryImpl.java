package com.kjcelis.social_meli.repositories.seller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjcelis.social_meli.dto.BuyerDTO;
import com.kjcelis.social_meli.dto.PostDTO;
import com.kjcelis.social_meli.dto.SellerDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

    private final ArrayList<SellerDTO> databaseSeller = new ArrayList<>();


    public void saveFollowB(Integer userId, Optional<BuyerDTO> buyerDTO) {
        if (findSellerById(userId).isPresent() && buyerDTO.isPresent()) {
            for (SellerDTO s : databaseSeller) {
                if (!isFollow(buyerDTO, s) && s.getUserId().equals(userId)) {
                    BuyerDTO buyerDTO1 = new BuyerDTO(buyerDTO.get().getUserId(), buyerDTO.get().getUserName());
                    s.getFollowers().add(buyerDTO1);
                    s.setFollowers_count(s.getFollowers().size());
                }
            }
        }
    }

    @Override
    public void savePost(Optional<SellerDTO> sellerById, PostDTO postDTO) {
        if (sellerById.isPresent()) {
            for (SellerDTO s : databaseSeller) {
                if (s.getUserId().equals(postDTO.getUserId())) {
                    s.getPosts().add(postDTO);
                }
            }
        }

    }

    @Override
    public void unfollowedBuye(SellerDTO sellerDTO, Integer userId) {
        for (int i = 0; i < sellerDTO.getFollowers().size(); i++) {
            if (sellerDTO.getFollowers().get(i).getUserId().equals(userId)) {
                sellerDTO.getFollowers().remove(i);
            }
        }
    }

    @Override
    public void savePromoPost(Optional<SellerDTO> sellerById, PostDTO pPostDTO) {
        if (sellerById.isPresent()) {
            for (SellerDTO s : databaseSeller) {
                if (s.getUserId().equals(pPostDTO.getUserId())) {
                    s.getPost_promo().add(pPostDTO);
                    s.setPromoproducts_count(s.getPost_promo().size());
                }
            }
        }
    }


    private Boolean isFollow(Optional<BuyerDTO> buyerDTO, SellerDTO s) {
        boolean isF = false;
        if (buyerDTO.isPresent()) {
            for (BuyerDTO i : s.getFollowers()) {
                if (i.getUserId().equals(buyerDTO.get().getUserId())) {
                    isF = true;
                }
            }
        }
        return isF;
    }

    public Optional<SellerDTO> findSellerById(Integer userId) {
        SellerDTO sellerDTO = null;
        for (SellerDTO s : databaseSeller) {
            if (s.getUserId().equals(userId)) {
                sellerDTO = s;
            }
        }
        return Optional.ofNullable(sellerDTO);


    }


    public void initListSeller() {
        List<SellerDTO> sellerDTOS;
        sellerDTOS = loadDatabase();
        for (SellerDTO s : sellerDTOS) {
            databaseSeller.add(new SellerDTO(s.getUserId(), s.getUserName(), new ArrayList<>(), 0, new ArrayList<>(), 0, new ArrayList<>()));
        }

    }


    private List<SellerDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/seller.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<SellerDTO> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<SellerDTO>> typeReference = new TypeReference<>() {
        };
        List<SellerDTO> sellerDTOS = null;
        try {
            sellerDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellerDTOS;
    }

}
