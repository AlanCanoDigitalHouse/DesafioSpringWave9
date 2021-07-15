package com.example.desafiospring.repositories;


import com.example.desafiospring.dtos.Post;
import com.example.desafiospring.dtos.User;
import com.example.desafiospring.exceptions.UserDontHavePostsException;

import java.util.List;
import java.util.Optional;

public interface SocialMediaRepository {

    Optional<User> findById(int userId);
    void saveFollowers(int userId, User user);
    void saveFollowed(int userId ,User user);
    Post savePost(Post post);
    List<Post> findPostsByUserId (Integer userId) throws UserDontHavePostsException;
    public List<Post> findPostsByUserIds(List<Integer> ids);
    void removeFollower(int userId, User user);
    void removeFollowed(int userId, User user);

}
