package com.meli.joescaos.socialmeli.socialmeli.dtos.responses;

import com.meli.joescaos.socialmeli.socialmeli.models.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerFollowingsDto {
    private int userId;
    private String userName;
    private List<SellerDto> followed = new ArrayList<>();
}
