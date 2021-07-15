package com.example.desafiospring.services.implementations;

import com.example.desafiospring.DTOS.requests.NewPostRequestDTO;
import com.example.desafiospring.entities.PostEntity;
import com.example.desafiospring.repository.interfaces.PostRepository;
import com.example.desafiospring.services.interfaces.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Integer addPost(NewPostRequestDTO newPostRequestDTO, Integer productId) {
        return postRepository.addPost(newPostRequestDTO, productId);
    }

    @Override
    public List<PostEntity> getRecentPostsOf(List<Integer> followedIds, String order) {
        return postRepository.getRecentPostsOf(followedIds, order);
    }

    @Override
    public Set<Integer> getPromoProductIDs(Integer userId) {
        return postRepository.getPromoProductIDs(userId);
    }

    @Override
    public List<PostEntity> getPromoPostsOf(Integer userId) {
        return postRepository.getPromoPostsOf(userId);
    }
}
