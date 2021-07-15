package com.meli.joescaos.socialmeli.socialmeli.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerFollowersCountDto {
    private int userId;
    private String userName;
    private int followers_count;
}
