package com.meli.joescaos.socialmeli.socialmeli.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {

    private int userId;
    private String userName;
    private List<Seller> following = new ArrayList<>();

    public void followSeller(Seller seller) {
        this.following.add(seller);
    }

    public void unfollowSeller(Seller seller) {
        this.following.remove(seller);
    }
}
