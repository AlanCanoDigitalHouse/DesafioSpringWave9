package com.mercadolibre.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.socialmeli.dtos.BuyerDTO;
import com.mercadolibre.socialmeli.dtos.SellerDTO;
import com.mercadolibre.socialmeli.dtos.UserDTO;
import com.mercadolibre.socialmeli.exceptions.BuyerNotFoundException;
import com.mercadolibre.socialmeli.exceptions.SellerAlreadyFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFollowedException;
import com.mercadolibre.socialmeli.exceptions.SellerNotFoundException;
import com.mercadolibre.socialmeli.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<SellerDTO> sellerList;
    private List<BuyerDTO> buyerList;

    @PostConstruct
    private void loadDB() {
        loadSellers();
        loadBuyers();
    }

    private void loadSellers() {
        try {
            InputStream in = this.getClass().getResourceAsStream("/static/sellers.json");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            sellerList = mapper.convertValue(jsonNode, new TypeReference<>() {});
        } catch (Exception ex) {
            System.out.println("Cannot load SELLER db: " + ex.getMessage());
            System.exit(-1);
        }
    }

    private void loadBuyers() {
        try {
            InputStream in = this.getClass().getResourceAsStream("/static/buyers.json");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            buyerList = mapper.convertValue(jsonNode, new TypeReference<>() {});
        } catch (Exception ex) {
            System.out.println("Cannot load BUYER db: " + ex.getMessage());
            System.exit(-1);
        }
    }

    @Override
    public SellerDTO getSellerById(Integer id) throws SellerNotFoundException {
        return sellerList.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SellerNotFoundException(id));
    }

    @Override
    public void removeFollower(Integer sellerId, Integer followerId) throws SellerNotFoundException, BuyerNotFoundException, SellerNotFollowedException {
        SellerDTO seller = getSellerById(sellerId);
        BuyerDTO buyer = getBuyerById(followerId);

        boolean result = seller.getFollowers().removeIf(f -> f.getUserId().equals(followerId));
        if(!result) {
            throw new SellerNotFollowedException(sellerId);
        }
        result = buyer.getFollowed().removeIf(f -> f.getUserId().equals(sellerId));
        if(!result) {
            throw new SellerNotFollowedException(sellerId);
        }
    }

    @Override
    public void addFollower(Integer sellerId, Integer followerId) throws SellerNotFoundException, BuyerNotFoundException, SellerAlreadyFollowedException {
        SellerDTO seller = getSellerById(sellerId);
        BuyerDTO buyer = getBuyerById(followerId);

        // check if already follow
        Optional<UserDTO> followerOptional = seller.getFollowers().stream()
                .filter(f -> f.getUserId().equals(followerId))
                .findFirst();
        if (followerOptional.isPresent()) {
            throw new SellerAlreadyFollowedException(followerId);
        }

        seller.getFollowers().add(new UserDTO(buyer.getUserId(), buyer.getUserName()));
        buyer.getFollowed().add(new UserDTO(seller.getUserId(), seller.getUserName()));
    }

    @Override
    public BuyerDTO getBuyerById(Integer id) throws BuyerNotFoundException {
        return buyerList.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BuyerNotFoundException(id));
    }

}
