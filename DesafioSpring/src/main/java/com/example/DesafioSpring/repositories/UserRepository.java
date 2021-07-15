package com.example.DesafioSpring.repositories;

import com.example.DesafioSpring.models.Buyer;
import com.example.DesafioSpring.models.Seller;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Repository

public class UserRepository {
    private HashMap<Integer, String> users = new HashMap<>();
    // userId , buyer/seller
    private HashMap<Integer, Seller> sellersDB = new HashMap<>();
    private HashMap<Integer, Buyer> buyersDB = new HashMap<>();
    private HashMap<Integer, Integer> buyerToSeller = new HashMap<>();
    // userId(buyer) , userId(seller)

    public Boolean findUserById(Integer userId) {
        var result = users.get(userId);
        if (result == null) {
            return false;
        }else {
            return true;
        }
    }

    public Boolean findSellerById(Integer userId) {
        var result = users.get(userId);
        if (result == null) {
            return false;
        }else if (result.equals("Buyer")){
                    return false;
                }else{
                    return true;
                }
    }

    public Boolean findBuyerById(Integer userId) {
        var result = users.get(userId);
        if (result == null) {
            return false;
        }else if (result.equals("Seller")){
            return false;
        }else{
            return true;
        }
    }

    public void addFollowLink(Integer userId, Integer userIdFollow) {
        buyerToSeller.put(userId,userIdFollow);
    }

    public Integer countFollowers(Integer userId) {
        AtomicReference<Integer> result = new AtomicReference<>(0);
        buyerToSeller.forEach((integer, integer2) -> {
            if (integer2.equals(userId)){
                result.getAndSet(result.get() + 1);
            }
        });
        return result.get();
    }

    public Seller getSeller(Integer userId) {
        Seller seller = sellersDB.get(userId);
        return seller;
    }

    public Buyer getBuyer(Integer userId) {
        Buyer buyer = buyersDB.get(userId);
        return buyer;
    }

    public List<Buyer> listFollowers(Integer userId) {
        Seller seller = sellersDB.get(userId);
        return seller.getFollowers();
    }

    // Listado de compradores a partir de un vendedor
    public List<Buyer> getBuyerlistFollowers(Integer userId) {
        List<Integer> buyers = new ArrayList<>();
        for (Map.Entry<Integer, Integer> item : buyerToSeller.entrySet()){
            Integer seller = item.getValue();
            if (seller == userId){
                Integer buyer = item.getKey();
                buyers.add(buyer);
            }
        }
        List<Buyer> result = new ArrayList<>();
        for ( Integer b : buyers) {
            result.add(buyersDB.get(b));
        }
        return result;
    }

    // Obtiene el listado de vendedores a partir de un comprador
    public List<Integer> getSellerIdList(Integer userId) {
        List<Integer> sellers = new ArrayList<>();
        for (Map.Entry<Integer, Integer> item : buyerToSeller.entrySet()){
            Integer buyer = item.getKey();
            if (buyer == userId){
                Integer seller = item.getValue();
                sellers.add(seller);
            }
        }
        return sellers;
    }

    public Boolean unfollowById(Integer userId, Integer userIdToUnfollow) {
        return buyerToSeller.remove(userId, userIdToUnfollow);
    }

    public Boolean isFollow(Integer userId, Integer userIdFollow) {
        for (Map.Entry<Integer, Integer> item : buyerToSeller.entrySet()){
            Integer buyer = item.getKey();
            Integer seller = item.getValue();
            if (buyer == userId & seller == userIdFollow){
                return true;
            }
        }
        return false;
    }

    public String load() {

        Buyer b1 = new Buyer(10, "Alan");
        Buyer b2 = new Buyer(11, "Miguel");
        Buyer b3 = new Buyer(12, "Brian");
        buyersDB.put(10, b1);
        users.put(10, "Buyer");
        buyersDB.put(11, b2);
        users.put(11, "Buyer");
        buyersDB.put(12, b3);
        users.put(12, "Buyer");

        Seller s1 = new Seller(25, "Mateo");
        Seller s2 = new Seller(35, "Marcos");
        Seller s3 = new Seller(45, "Lucas");
        Seller s4 = new Seller(55, "Juan");
        Seller s5 = new Seller(65, "Pablo");
        sellersDB.put(25, s1);
        users.put(25, "Seller");
        sellersDB.put(35, s2);
        users.put(35, "Seller");
        sellersDB.put(45, s3);
        users.put(45, "Seller");
        sellersDB.put(55, s4);
        users.put(55, "Seller");
        sellersDB.put(65, s5);
        users.put(65, "Seller");

        System.out.println("sellersDB = " + sellersDB);
        System.out.println("buyersDB = " + buyersDB);
        return "Full load";
    }


}
