package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.request.PostDto;
import com.example.socialmeli.exceptions.UserNotFoundException;
import com.example.socialmeli.models.User;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public interface IRepositorySocialMeli {

    List<User> create();

    void saveFollow(Integer userId, Integer userIdToFollow) throws UserNotFoundException;

    Optional<User>  findUserById(Integer userId);

    List<User> saveUnfollow(Integer userId, Integer userIdToUnfollow) throws UserNotFoundException;

    HttpStatus saveNewPost(PostDto postDto) throws UserNotFoundException;

    List<PostDto> getPost(Integer userId) throws UserNotFoundException;


}
