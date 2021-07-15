package com.mercadolibre.socialmeli.dtos.User;

import com.mercadolibre.socialmeli.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserFollowedDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followed;

    public UserFollowedDTO(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.followed = user.getFollowed();
    }

}
