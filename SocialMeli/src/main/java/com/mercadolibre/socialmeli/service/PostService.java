package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.PostPromoRequestDTO;
import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
import com.mercadolibre.socialmeli.dto.response.PostCountPromoResponseDTO;
import com.mercadolibre.socialmeli.dto.response.PostResponseDTO;
import com.mercadolibre.socialmeli.entity.Post;
import com.mercadolibre.socialmeli.entity.User;
import com.mercadolibre.socialmeli.repository.PostRepository;
import com.mercadolibre.socialmeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;
    UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Integer newPost(PostRequestDTO postRequestDTO){
        return this.postRepository.createPost(postRequestDTO);
    }

    public Integer newPostPromo(PostPromoRequestDTO postPromoRequestDTO){
        return this.postRepository.createPostPromo(postPromoRequestDTO);
    }

    public PostCountPromoResponseDTO getPostsPromoCount(int userId){
        User user = this.userRepository.findUserById(userId);
        return this.postRepository.countPromosInPostsByUser(user);
    }

    public PostResponseDTO getPostsFollowed(int userId, String order){
        User user = this.userRepository.findUserById(userId);
        List<Post> posts = this.postRepository.getPostsFollowed(user, order);
        return new PostResponseDTO(userId, posts);
    }

    public PostResponseDTO getPostsWithPromoByUser(int userId){
        User user = this.userRepository.findUserById(userId);
        List<Post> posts = this.postRepository.getPostsWithPromoByUser(user);
        return new PostResponseDTO(userId, posts);
    }
}
