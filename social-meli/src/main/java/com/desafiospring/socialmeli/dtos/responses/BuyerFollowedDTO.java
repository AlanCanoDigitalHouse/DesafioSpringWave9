package com.desafiospring.socialmeli.dtos.responses;

import com.desafiospring.socialmeli.dtos.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerFollowedDTO {

    private int userId;
    private String userName;
    private List<User> followed;

}
