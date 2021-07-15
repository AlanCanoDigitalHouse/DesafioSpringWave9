package com.example.socialmeli.dtos;

import com.example.socialmeli.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private List<FollowDto> following;
    private List<FollowDto> followers;

    public void addFollowing(UserDto userToFollow){
        if (this.following.stream().anyMatch(f -> f.getUserId().equals(userToFollow.getUserId())))
            throw new NotFoundException("El usuario ya sigue al usuario");
        following.add(
                new FollowDto(
                        userToFollow.getUserId(),
                        userToFollow.getUserName()
                )
        );
    }

    public void addFollower(UserDto userFollower){
        if (this.followers.stream().anyMatch(f -> f.getUserId().equals(userFollower.getUserId())))
            throw new NotFoundException("El usuario es seguido por el usuario indicado");
        followers.add(
                new FollowDto(
                        userFollower.getUserId(),
                        userFollower.getUserName()
                )
        );
    }

    public void removeFollower(Integer id){
        followers.removeIf(u -> u.getUserId().equals(id));
    }

    public void removeFollowing(Integer id){
        following.removeIf(u -> u.getUserId().equals(id));
    }
}
