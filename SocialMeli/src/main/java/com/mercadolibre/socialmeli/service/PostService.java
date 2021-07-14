package com.mercadolibre.socialmeli.service;

import com.mercadolibre.socialmeli.dto.request.PostRequestDTO;
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

    public PostResponseDTO getPostsFollowed(int userId){
        User user = this.userRepository.findUserById(userId);
        List<Post> posts = this.postRepository.getPostsFollowed(user);
        return new PostResponseDTO(userId, posts);
    }
}
