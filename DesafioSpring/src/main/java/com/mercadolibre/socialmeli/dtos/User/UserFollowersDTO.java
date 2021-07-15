package com.mercadolibre.socialmeli.dtos.User;

import com.mercadolibre.socialmeli.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserFollowersDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followers;

    public UserFollowersDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.followers = user.getFollowers();
    }
}
