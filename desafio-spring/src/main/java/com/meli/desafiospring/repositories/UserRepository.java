package com.meli.desafiospring.repositories;

import com.meli.desafiospring.DTOs.PostDTO;
import com.meli.desafiospring.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{

    List<User> users;

    public UserRepository() {
        this.users = new ArrayList<User>();
    }

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        //check if both present; if not, raise exception.
        User follower = this.findById(userId).get();
        User followed = this.findById(userIdToFollow).get();
        if (Objects.isNull(follower) || Objects.isNull(followed))
            throw new RuntimeException("User was not found");
        follower.follow(followed);
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        User follower = this.findById(userId).get();
        User unfollowed = this.findById(userIdToUnfollow).get();
        if (Objects.isNull(follower) || Objects.isNull(unfollowed))
            throw new RuntimeException("User was not found");
        follower.unfollow(unfollowed);
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


    private Optional<User> findById(Integer userId) {
        return users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
    }
}
