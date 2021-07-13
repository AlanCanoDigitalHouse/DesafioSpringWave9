package com.example.socialmeli.models;

import com.example.socialmeli.dtos.PostDto;
import com.example.socialmeli.dtos.responses.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Data
public class User {
    Integer id;
    String name;
    ArrayList<User> followers;
    ArrayList<User> followed;
    ArrayList<ResponsePostDto> posts;

    public User(Integer id, String name){
        this.id = id;
        this.name = name;
        followed = new ArrayList<>();
        followers = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public void addFollowed(User user) {
        if(!followed.contains(user))
            followed.add(user);
    }

    public void addFollower(User user) {
        if(!followers.contains(user))
            followers.add(user);
    }

    public void removeFollowed(User userToUnfollow) {
        if(followed.contains(userToUnfollow))
            followed.remove(userToUnfollow);
    }

    public void removeFollower(User user) {
        if(followers.contains(user))
            followers.remove(user);
    }

    public ResponseCantFollowersDto getCantFollowers() {
        return new ResponseCantFollowersDto(id,name,followers.size());
    }


    private void sortByName(ArrayList<ResponseUserDto> followersListsDto, String order){
        if(order.contains("name_asc"))
            Collections.sort(followersListsDto, Comparator.comparing(ResponseUserDto::getUserName));
        else if(order.contains("name_desc"))
            Collections.sort(followersListsDto, Comparator.comparing(ResponseUserDto::getUserName).reversed());

    }

    public ResponseFollowersDto getInfoFollowers(String order) {
        ResponseFollowersDto responseFollowersDto = new ResponseFollowersDto();
        ArrayList<ResponseUserDto> followersListDto = new ArrayList<>();
        responseFollowersDto.setUserId(id);
        responseFollowersDto.setUsername(name);

        for(User f: followers){
            followersListDto.add(new ResponseUserDto(f.getId(),f.getName()));
        }

        sortByName(followersListDto,order);

        responseFollowersDto.setFollowers(followersListDto);
        return responseFollowersDto;
    }

    public ResponseFollowedDto getInfoFollowed(String order) {
        ResponseFollowedDto responseFollowedDto = new ResponseFollowedDto();
        ArrayList<ResponseUserDto> followedListDto = new ArrayList<>();
        responseFollowedDto.setUserId(id);
        responseFollowedDto.setUsername(name);

        for(User f: followed){
            followedListDto.add(new ResponseUserDto(f.getId(),f.getName()));
        }

        sortByName(followedListDto,order);

        responseFollowedDto.setFollowed(followedListDto);
        return responseFollowedDto;
    }

    public void addPost(PostDto postDto) {
        ResponsePostDto responsePostDto= new ResponsePostDto();
        responsePostDto.setCategory(postDto.getCategory());
        responsePostDto.setDate(postDto.getDate());
        responsePostDto.setDetail(postDto.getDetail());
        responsePostDto.setPrice(postDto.getPrice());
        posts.add(responsePostDto);
    }

    private void sortByDate(ArrayList<ResponsePostDto> followersPosts, String order){
        followersPosts.get(1).getDate();
        if(order.contains("date_asc"))
            Collections.sort(followersPosts, Comparator.comparing(ResponsePostDto::getDate));
        else if(order.contains("date_desc"))
            Collections.sort(followersPosts, Comparator.comparing(ResponsePostDto::getDate).reversed());

    }

    public ResponsePostsListDto getPostsList(String order) {
        ResponsePostsListDto responsePostsListDto = new ResponsePostsListDto();
        responsePostsListDto.setUserId(id);
        ArrayList<ResponsePostDto> followersPosts = new ArrayList<>();
        for(User f: followed){
            ArrayList<ResponsePostDto> followerPosts = f.getPosts();
            if(followerPosts!=null){
                for(ResponsePostDto p: followerPosts){
                    followersPosts.add(p);
                }
            }
        }

        sortByDate(followersPosts,order);

        responsePostsListDto.setPosts(followersPosts);
        return responsePostsListDto;
    }
}
