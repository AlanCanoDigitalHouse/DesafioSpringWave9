package com.mercadolibre.socialmedia.repositories;

import com.mercadolibre.socialmedia.dtos.PostDto;
import com.mercadolibre.socialmedia.dtos.User;
import com.mercadolibre.socialmedia.dtos.UserDto;
import com.mercadolibre.socialmedia.exceptions.UserIdException;
import com.mercadolibre.socialmedia.utils.DataBase;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.*;


@Repository
public class SocialMediaRepositoryImpl implements ISocialMediaRepository {
    private final Map<Integer, UserDto> database = DataBase.initDataBase();

    public SocialMediaRepositoryImpl() throws ParseException { }

    public UserDto findUserById(Integer userId){
        UserDto user = database.get(userId);
        if(!Objects.nonNull(user)){
            throw new UserIdException("The userId: " + userId + " not found.");
        }
        return user;
    }

    @Override
    public List<PostDto> getPosts(Integer userId) {
        UserDto user = this.findUserById(userId);
        List<PostDto> posts = user.getPosts();
        return posts;
    }

    @Override
    public List<User> getFollowed(Integer userId) throws UserIdException{
        UserDto user = this.findUserById(userId);
        List<User> listFollowed;
        listFollowed = user.getFollowed();
        return listFollowed;
    }

}
