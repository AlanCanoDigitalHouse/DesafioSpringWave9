package com.example.demo.services;

import com.example.demo.dtos.PostDto;
import com.example.demo.dtos.SimplePostDto;
import com.example.demo.dtos.response.PostsListResponseDto;
import com.example.demo.dtos.response.PromoCountResponseDto;
import com.example.demo.dtos.response.PromoPostsFromUserResponseDto;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.PostService;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.PostServiceUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Override
    public void createPostFor(SimplePostDto element, User user) {
        user.addPost(new PostDto (element.getUserId(), element.getId_post() , element.getDate() , element.getDetail() ,
                element.getCategory() , element.getPrice(), false , 0));
    }

    @Override
    public void createPromoPostFor(PostDto postDto, User user) {
        user.addPost(postDto);
    }

    @Override
    public PostsListResponseDto listTwoWeeksPosts(int user, Map<Integer,User> usersList, String order) {
        Date today = new Date();
        List<PostDto> foundedPosts = new ArrayList<>();
        for (User followed : usersList.get(user).getFollowed()){
            for (PostDto post : followed.getPosts()){
                if (DateUtils.weeksBetween(post.getDate() , today) <= 2){
                    foundedPosts.add(post);
                }
            }
        }
        if(order != null) {PostServiceUtils.orderByDate(order , foundedPosts);}
        return new PostsListResponseDto(user , PostServiceUtils.parsePostToResponseDto(foundedPosts));
    }

    @Override
    public PromoCountResponseDto countPromoPosts(User user) {
        int counter = 0 ;
        for (PostDto post : user.getPosts()){
            counter += post.checkIfPostIsPromo();
        }
        return new PromoCountResponseDto(user.getUserId() , user.getUserName() , counter);
    }

    @Override
    public PromoPostsFromUserResponseDto listPromoPosts(User user) {
        List<PostDto> postsList = new ArrayList<>();
        for (PostDto post : user.getPosts()){
            if (post.isHasPromo()){
                postsList.add(post);
            }
        }
        return new PromoPostsFromUserResponseDto(user.getUserId() , user.getUserName(), PostServiceUtils.parsePostToResponseDto(postsList));
    }
}

