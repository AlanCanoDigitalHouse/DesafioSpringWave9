package com.example.demo.Services;

import com.example.demo.DTOs.Request.PostRequestDTO;
import com.example.demo.Entities.Post;
import com.example.demo.Entities.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IUserService {
     boolean followAuser(Integer userId, Integer userToFollow);
     List<User> followersOf (Integer userId);
     int followerSize(Integer userId);
     List<User> followedByUserByID(Integer userId);
     boolean newPost (PostRequestDTO postRequestDTO) throws ParseException;
     List<Post> postList (Integer userId);
     boolean unfollowAuser(Integer userId, Integer userToFollow);

}
