package com.mercadolibre.socialmedia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto extends User{
    private List<User> followed;
    private List<User> followers;
    private List<PostDto> posts;
    
    public UserDto(Integer userId, String userName, List<User> followed, List<User> followers, List<PostDto> posts){
        super(userId, userName);
        this.followed = followed;
        this.followers = followers;
        this.posts = posts;
    }

}
