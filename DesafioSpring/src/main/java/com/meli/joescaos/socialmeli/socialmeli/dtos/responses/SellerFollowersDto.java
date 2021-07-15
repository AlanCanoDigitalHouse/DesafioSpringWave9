package com.meli.joescaos.socialmeli.socialmeli.dtos.responses;

import com.meli.joescaos.socialmeli.socialmeli.models.Buyer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerFollowersDto {

    private int userId;
    private String userName;
    private List<BuyerDto> followers = new ArrayList<>();


}
