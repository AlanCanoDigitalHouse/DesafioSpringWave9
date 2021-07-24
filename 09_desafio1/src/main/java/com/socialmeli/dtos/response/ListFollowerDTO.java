package com.socialmeli.dtos.response;

import com.socialmeli.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ListFollowerDTO extends UserDTO {

    private ArrayList<UserDTO> followers;

    public ListFollowerDTO(Integer userId, String userName, ArrayList<UserDTO> followers) {
        super(userId, userName);
        this.followers = followers;
    }
}
