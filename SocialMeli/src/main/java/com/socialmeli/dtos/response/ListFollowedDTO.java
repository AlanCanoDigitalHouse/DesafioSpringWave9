package com.socialmeli.dtos.response;

import com.socialmeli.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ListFollowedDTO extends UserDTO {

    private ArrayList<UserDTO> followed;

    public ListFollowedDTO(Integer userId, String userName, ArrayList<UserDTO> followed) {
        super(userId, userName);
        this.followed = followed;
    }
}
