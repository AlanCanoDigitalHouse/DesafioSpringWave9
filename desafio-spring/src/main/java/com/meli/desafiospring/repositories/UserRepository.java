package com.meli.desafiospring.repositories;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository{


    @Override
    public HttpStatus follow(Integer userId, Integer userIdToFollow) {
        return null;
    }

    @Override
    public HttpStatus unfollow(Integer userId, Integer userIdToUnfollow) {
        return null;
    }

    @Override
    public Integer followers_count(Integer userId) {
        return null;
    }

    @Override
    public List<User> followers_list(Integer userId) {
        return null;
    }

    @Override
    public List<User> followed_list(Integer userId) {
        return null;
    }

    @Override
    public HttpStatus newPost(Integer userId, PostDTO postDTO) {
        return null;
    }

    @Override
    public List<PostDTO> lastPostedItems(Integer userId) {
        return null;
    }
}
