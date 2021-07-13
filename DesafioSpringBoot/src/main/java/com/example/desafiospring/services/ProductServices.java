package com.example.desafiospring.services;

import com.example.desafiospring.dtos.request.PostRequestDto;
import com.example.desafiospring.dtos.response.PostResponseDto;
import com.example.desafiospring.dtos.response.ProductResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import com.example.desafiospring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

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

    public List<PostResponseDto> getPostFromUser(Integer userId){
        var posts = productRepository.getPosts().stream()
                .filter(postResponseDto -> postResponseDto.getUserId() == userId);
        var list = posts.collect(Collectors.toList());
        if (list.size()>0){
            return list;
        }else{
            throw new LogicValidationException("No post found for this user");
        }
    }

}
