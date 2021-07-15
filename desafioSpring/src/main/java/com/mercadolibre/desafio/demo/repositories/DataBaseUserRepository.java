package com.mercadolibre.desafio.demo.repositories;

import com.mercadolibre.desafio.demo.exceptions.userException.exceptions.RepeatUserIdException;
import com.mercadolibre.desafio.demo.models.BuyerModel;
import com.mercadolibre.desafio.demo.models.SellerModel;
import com.mercadolibre.desafio.demo.models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DataBaseUserRepository {
    private List<UserModel> list = new ArrayList<>();
    private Map<Integer,List<BuyerModel>> follorwRegister = new HashMap<Integer, List<BuyerModel>>();

    // load sellers
    public void loadSellers(){
        this.list.forEach(us -> follorwRegister.put(us.getUserId(), new ArrayList<>()));
    }

    // get list
    public List<UserModel> getList() {
        return list;
    }

    // set list
    public void setList(List<UserModel> list) {
        this.list = list;
    }

//    public Map<Integer, List<BuyerModel>> getFollorwRegister() {
//        return follorwRegister;
//    }

//    public void setFollorwRegister(Map<Integer, List<BuyerModel>> follorwRegister) {
//        this.follorwRegister = follorwRegister;
//    }

    // get follower
    public Optional<UserModel> getFollower(Integer userId){
        return this.list.stream().filter(follow -> follow.getUserId().equals(userId)).findFirst();
    }

    // add fpllower
    public void addFollower(Integer sellerId, BuyerModel buyerModel) {
        if (!this.follorwRegister.get(sellerId).contains(buyerModel))
            this.follorwRegister.get(sellerId).add(buyerModel);
        else
            throw new RepeatUserIdException("this user is already following the seller",sellerId, buyerModel.getUserId());
    }

    // add seller has key of hashmap
    public void addSeller(Integer sellerId) {
        this.follorwRegister.computeIfAbsent(sellerId, k -> new ArrayList<>());
    }

    // remove one follower
    public void removeFollower(Integer sellerId, BuyerModel buyerModel) {
        this.follorwRegister.get(sellerId).removeIf(buyer-> buyer.equals(buyerModel));
    }

    // return value all followers
    public Integer countFollowers(Integer sellerId){
        return this.follorwRegister.get(sellerId).size();
    }

    // get all followers
    public List<BuyerModel> getFollowersList(Integer sellerId) {
        return this.follorwRegister.get(sellerId);
    }

    // get all followed list
    public List<SellerModel> getFollowedList(UserModel buyerDTO) {
        List<SellerModel> followedList = new ArrayList<>();
        BuyerModel buyerToFind = new BuyerModel(buyerDTO.getUserId(),buyerDTO.getUserName());
        for (Map.Entry<Integer, List<BuyerModel>> entry : this.follorwRegister.entrySet()) {
            if(entry.getValue().contains(buyerToFind)){
                Optional<UserModel> sellerDTO = this.getFollower(entry.getKey());
                sellerDTO.ifPresent(usuarioDTO -> followedList.add(new SellerModel(usuarioDTO.getUserId(), usuarioDTO.getUserName())));
            }
        }

        return  followedList;
    }


}
