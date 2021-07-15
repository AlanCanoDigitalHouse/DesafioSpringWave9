package com.desafio.spring.services;

import com.desafio.spring.dtos.PostDto;
import com.desafio.spring.repositories.PostRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    private final PostRespository postRepository;

    public PostServiceImpl(PostRespository postRespository){this.postRepository = postRespository;}

    @Override
    public void newPost(PostDto post) {
        postRepository.newPost(post);
    }

    @Override
    public List<PostDto> getPostSeller(Integer userId, String date) {
        return postRepository.findPostsLasted(userId, date);
    }



}
