package com.example.desafiospring.services;

import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.PostResponseDto;
import com.example.desafiospring.dtos.response.ProductResponseDto;
import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import com.example.desafiospring.repositories.ProductRepository;
import com.example.desafiospring.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices {
    private ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PostResponseDto createNewPost(PostRequestDto postResponseDto){
        var productRequest = postResponseDto.getDetail();
        var product = new ProductResponseDto(productRequest);
        var post = new PostResponseDto(postResponseDto.getUserId(),
                postResponseDto.getDate(),postResponseDto.getCategory(),postResponseDto.getPrice());
        post.addProduct(product);
        productRepository.create(post);
        return post;
    }

    public List<PostResponseDto> getPosts(List<UserResponseDto> listFollowed){
        List<PostResponseDto> posts = new ArrayList<>();
        for (UserResponseDto user:listFollowed) {
            var sellerId = user.getId();
            var postFromSeller = productRepository.getPosts().stream()
                    .filter(postResponseDto -> postResponseDto.getUserId() == sellerId).collect(Collectors.toList());
            posts.addAll(postFromSeller);
        }
        if (posts.size()>0){
            return posts;
        }else{
            throw new LogicValidationException("This user has not followed anyone or the followed sellers hasn't post");
        }
    }

}
