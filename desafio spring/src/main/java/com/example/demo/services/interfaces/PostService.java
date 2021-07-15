package com.example.demo.services.interfaces;

import com.example.demo.dtos.PostDto;
import com.example.demo.dtos.SimplePostDto;
import com.example.demo.dtos.response.PostsListResponseDto;
import com.example.demo.dtos.response.PromoCountResponseDto;
import com.example.demo.dtos.response.PromoPostsFromUserResponseDto;
import com.example.demo.models.User;

import java.util.Map;

public interface PostService {

    public void createPostFor(SimplePostDto postDto, User user);

    public void createPromoPostFor(PostDto promoPostDto, User user);

    public PostsListResponseDto listTwoWeeksPosts(int user, Map<Integer,User> usersList, String order);

    public PromoCountResponseDto countPromoPosts(User user);

    public PromoPostsFromUserResponseDto listPromoPosts(User user);
}
