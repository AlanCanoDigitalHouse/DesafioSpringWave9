package com.meli.joescaos.socialmeli.socialmeli.models;

import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.BuyerDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.SellerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    private int userId;
    private String userName;
    private List<Buyer> followers = new ArrayList<>();

    public void followedBy(Buyer buyer) {
        this.followers.add(buyer);
    }
}
