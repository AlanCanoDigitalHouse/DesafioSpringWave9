package com.meli.itbootcamp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meli.itbootcamp.model.User;
import com.meli.itbootcamp.model.UserNonSeller;
import com.meli.itbootcamp.model.UserSeller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
/**
 * DTO for follows and followers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollowsDTO {
    private UserDTO user;
    private List<UserDTO> followed;
    private List<UserDTO> followers;

    /*
     */

    public UserFollowsDTO(UserSeller seller, List<UserDTO>followers) {
        this.user = new UserDTO(seller);
        this.followers = followers;

    }
    public UserFollowsDTO(UserNonSeller nonSeller, List<UserDTO>followed) {
        this.user = new UserDTO(nonSeller);
        this.followed = followed;

    }
}

