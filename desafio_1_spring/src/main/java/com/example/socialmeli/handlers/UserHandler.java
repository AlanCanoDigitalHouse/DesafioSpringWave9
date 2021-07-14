package com.example.socialmeli.handlers;

import com.example.socialmeli.dtos.requests.RequestPostDto;
import com.example.socialmeli.dtos.responses.*;
import com.example.socialmeli.models.Product;
import com.example.socialmeli.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class UserHandler {
    public static ResponseCantFollowersDto getCantFollowers(User user) {
        ResponseCantFollowersDto responseCantFollowersDto = new ResponseCantFollowersDto();
        responseCantFollowersDto.setUserId(user.getId());
        responseCantFollowersDto.setUserName(user.getName());
        responseCantFollowersDto.setFollowersCount(user.getFollowers().size());
        return responseCantFollowersDto;
    }

    private static void sortByName(ArrayList<ResponseUserDto> followersListsDto, String order){
        if(order.contains("name_asc"))
            Collections.sort(followersListsDto, Comparator.comparing(ResponseUserDto::getUserName));
        else if(order.contains("name_desc"))
            Collections.sort(followersListsDto, Comparator.comparing(ResponseUserDto::getUserName).reversed());

    }

    public static ResponseFollowersDto getFollowersInfo(User user,String order) {
        ResponseFollowersDto responseFollowersDto = new ResponseFollowersDto();
        ArrayList<ResponseUserDto> followersListDto = new ArrayList<>();
        responseFollowersDto.setUserId(user.getId());
        responseFollowersDto.setUsername(user.getName());

        ArrayList<User> followers = user.getFollowers();
        for(User f: followers){
            followersListDto.add(new ResponseUserDto(f.getId(),f.getName()));
        }

        sortByName(followersListDto,order);

        responseFollowersDto.setFollowers(followersListDto);
        return responseFollowersDto;
    }

    public static ResponseFollowedDto getFollowedInfo(User user,String order) {
        ResponseFollowedDto responseFollowedDto = new ResponseFollowedDto();
        ArrayList<ResponseUserDto> followedListDto = new ArrayList<>();
        responseFollowedDto.setUserId(user.getId());
        responseFollowedDto.setUsername(user.getName());

        ArrayList<User> followed = user.getFollowed();

        for(User f: followed){
            followedListDto.add(new ResponseUserDto(f.getId(),f.getName()));
        }

        sortByName(followedListDto,order);

        responseFollowedDto.setFollowed(followedListDto);
        return responseFollowedDto;
    }

    public static void addUserPost(User user, RequestPostDto requestPostDto, Product product) {
        //ESTO DESPUES HACERLO EL EL POST HANDLER
        ResponsePostDto responsePostDto= new ResponsePostDto();
        responsePostDto.setCategory(requestPostDto.getCategory());
        responsePostDto.setDate(requestPostDto.getDate());
        responsePostDto.setDetail(product);
        responsePostDto.setPrice(requestPostDto.getPrice());

        user.addPost(responsePostDto);
    }

    private static void sortByDate(ArrayList<ResponsePostDto> followersPosts, String order){
        followersPosts.get(1).getDate();
        if(order.contains("date_asc"))
            Collections.sort(followersPosts, Comparator.comparing(ResponsePostDto::getDate));
        else if(order.contains("date_desc"))
            Collections.sort(followersPosts, Comparator.comparing(ResponsePostDto::getDate).reversed());

    }

    public static ResponsePostsListDto getUserPostsList(User user, String order) {
        ResponsePostsListDto responsePostsListDto = new ResponsePostsListDto();
        responsePostsListDto.setUserId(user.getId());
        ArrayList<ResponsePostDto> followersPosts = new ArrayList<>();

        ArrayList<User> followed = user.getFollowed();

        for(User f: followed){
            ArrayList<ResponsePostDto> followerPosts = f.getPosts();
            if(followerPosts!=null){
                for(ResponsePostDto p: followerPosts){
                    followersPosts.add(p);
                }
            }
        }

        if(followersPosts.size()>1)
            sortByDate(followersPosts,order);

        responsePostsListDto.setPosts(followersPosts);
        return responsePostsListDto;
    }
}
