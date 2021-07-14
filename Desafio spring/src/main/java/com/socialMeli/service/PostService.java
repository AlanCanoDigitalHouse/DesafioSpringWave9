package com.socialMeli.service;

import com.socialMeli.dto.request.product.PostInfoToCreateDTO;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.PostBuilder;
import com.socialMeli.model.PostModel;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.PostRepository;
import com.socialMeli.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.ParseException;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void addNewPost(@Valid PostInfoToCreateDTO dataPost) throws ParseException, ModelNotExists, ModelAlreadyExists {
        UserModel userModel = userRepository.findById(dataPost.getUserId());
        PostModel postModel = new PostBuilder(postRepository.getNextId())
                .setBrand(dataPost.getDetail().getBrand())
                .setCategory(dataPost.getCategory())
                .setColor(dataPost.getDetail().getColor())
                .setDate(dataPost.getDate())
                .setNotes(dataPost.getDetail().getNotes())
                .setPrice(dataPost.getPrice())
                .setProduct_id(postRepository.getNextId())
                .setType(dataPost.getDetail().getType())
                .setProductName(dataPost.getDetail().getProductName())
                .setUserId(userModel.getId())
                .build();

        postRepository.insert(postModel);
    }

}
