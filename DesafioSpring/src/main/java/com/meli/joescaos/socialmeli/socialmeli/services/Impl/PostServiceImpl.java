package com.meli.joescaos.socialmeli.socialmeli.services.Impl;

import com.meli.joescaos.socialmeli.socialmeli.dtos.requests.PostRequestDto;
import com.meli.joescaos.socialmeli.socialmeli.dtos.responses.PostsListDto;
import com.meli.joescaos.socialmeli.socialmeli.models.Post;
import com.meli.joescaos.socialmeli.socialmeli.repositories.PostRepository;
import com.meli.joescaos.socialmeli.socialmeli.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    // Attributes
    private final PostRepository postRepository;

    // Constructor
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     *
     * @param postRequest
     */
    @Override
    public void createPost(PostRequestDto postRequest) {
        Post post = new Post();
        post.setUserId(postRequest.getUserId());
        post.setDate(postRequest.getDate());
        post.setDetail(postRequest.getDetail());
        post.setCategory(postRequest.getCategory());
        post.setPrice(postRequest.getPrice());

        postRepository.savePost(post);
    }

}
