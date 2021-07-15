package com.example.demo.Services;

import com.example.demo.DTOs.FollowedPostsDTO;
import com.example.demo.DTOs.PostDTO;
import com.example.demo.DTOs.PostsDTO;
import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Handlers.ValidateUser;
import com.example.demo.Models.PostModel;
import com.example.demo.Repositories.IPostRepository;
import com.example.demo.Repositories.IUserRepository;
import com.example.demo.Services.Mapper.PostMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    IPostRepository postRepository;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void createPost(PostDTO post) throws ExceptionHandler {
        postRepository.createProduct(PostMapper.productToModel(post.getDetail()));
        postRepository.createPost(PostMapper.postToModel(post));
    }

    @Override
    public PostsDTO getPostsByUserId(int userId) throws ExceptionHandler {
        String userName = userRepository.getUserById(userId).getUserName();
        List<PostModel> postList = postRepository.getUserPostsById(userId);
        return new PostsDTO(userId, userName, postList);
    }

    @Override
    public FollowedPostsDTO getFollowedPosts(int userId, String order, int daysBefore) throws ExceptionHandler {
        return postRepository.getFollowedPosts(userId, order, daysBefore);
    }

}
