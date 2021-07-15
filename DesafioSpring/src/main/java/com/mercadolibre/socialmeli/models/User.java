package com.mercadolibre.socialmeli.models;

import com.mercadolibre.socialmeli.dtos.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private List<UserDTO> followers;
    private List<UserDTO> followed;


    public User() {
        followers = new ArrayList<>();
        followed = new ArrayList<>();
    }

    public void addFollower(UserDTO userDTO) {
        followers.add(userDTO);
    }

    public void addFollowed(UserDTO userDTO) {
        followed.add(userDTO);
    }

    public Integer countFollowers() {
        return followers.size();
    }

    public void deleteFollower(UserDTO userDTO) {
        followers.remove(userDTO);
    }

    public void deleteFollowed(UserDTO userDTO) {
        followed.remove(userDTO);
    }
}
