package com.mercadolibre.socialmedia.repositories;

import com.mercadolibre.socialmedia.dtos.PostDto;
import com.mercadolibre.socialmedia.dtos.User;
import com.mercadolibre.socialmedia.dtos.UserDto;

import java.util.List;

public interface ISocialMediaRepository {
    UserDto findUserById(Integer userId);

    List<User> getFollowed(Integer userId);
    List<PostDto> getPosts(Integer userId);
}
