package com.example.demo.repository;

import com.example.demo.dtos.PostDto;
import com.example.demo.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class PostRepository {

    private List<PostDto> posts;
    private List<Product> products;

    public void addPost(PostDto postDto){
        this.posts.add(postDto.getId_post() , postDto);
        this.products.add(postDto.getDetail().getProduct_id() , postDto.getDetail());
    }

    public PostDto get(int postId){
        return this.posts.get(postId);
    }

    public Date getDateFrom(int postId){
        return this.posts.get(postId).getDate();
    }

}
