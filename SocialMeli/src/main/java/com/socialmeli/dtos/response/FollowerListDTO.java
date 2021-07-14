package com.socialmeli.dtos.response;

import com.socialmeli.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FollowerListDTO extends UserDTO {

    private ArrayList<UserDTO> followers;

    public FollowerListDTO(Integer userId, String userName, ArrayList<UserDTO> followers) {
        super(userId, userName);
        this.followers = followers;
    }
}
