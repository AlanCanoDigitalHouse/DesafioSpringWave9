package com.example.desafiospring.services;

import com.example.desafiospring.dtos.createData.Sellers;
import com.example.desafiospring.dtos.createData.Users;
import com.example.desafiospring.dtos.request.NewPostDto;
import com.example.desafiospring.dtos.response.FollowersCountDto;
import com.example.desafiospring.dtos.response.FollowersListDto;
import com.example.desafiospring.dtos.response.SellersFollowedListDto;
import com.example.desafiospring.dtos.response.UserSeller2WeeksListDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserServices {
    List<Users> createUsers(String name);
    List<Sellers> createSellers(String name);
    boolean verifyUser(Integer userId);
    boolean verifySeller(Integer sellerId);
    ResponseEntity<HttpStatus> follow(Integer userId, Integer sellerId);
    FollowersCountDto usersSellersCountDto(int sellerId);
    FollowersListDto followersList(int sellerId, String order);
    SellersFollowedListDto followedList(int userId, String order);
    ResponseEntity<HttpStatus> createPost(NewPostDto post);
    UserSeller2WeeksListDto postList(int userId, String order);
    ResponseEntity<HttpStatus> unFollow(Integer userId, Integer sellerId);
}
