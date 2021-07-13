package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.response.PostResponseDto;
import com.example.desafiospring.exceptions.LogicValidationException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements CRUD<PostResponseDto> {
    private List<PostResponseDto> posts;

    public ProductRepository() {
        this.posts = new ArrayList<>();
    }


    public PostResponseDto getById(Integer id) {
        var product = posts.stream()
                .filter(postResponseDto -> postResponseDto.getId_post() == id)
                .findFirst();
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new LogicValidationException("Product not found");
        }
    }

    @Override
    public PostResponseDto create(PostResponseDto product) {
        var result = posts.add(product);
        if (result) {
            return product;
        } else {
            throw new LogicValidationException("the user can't be added");
        }
    }

    @Override
    public void update(PostResponseDto post) {
        var index = posts.indexOf(post);
        if (index != -1) {
            posts.set(index, post);
        } else {
            throw new LogicValidationException("Product not found");
        }
    }

    public void delete(Integer id) {
        var product = posts.stream()
                .filter(postResponseDto -> postResponseDto.getId_post() == id)
                .findFirst();
        if (product.isPresent()) {
            posts.remove(product);
        } else {
            throw new LogicValidationException("Product not found");
        }
    }

    public List<PostResponseDto> getPosts(){
        return this.posts;
    }
    public void  setPosts(List<PostResponseDto> posts){
        this.posts = posts;
    }
}
