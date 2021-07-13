package com.desafiospring.socialmeli.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerFollowersCountDTO {

    private int userId;
    private String userName;
    private int followers_count;
}
