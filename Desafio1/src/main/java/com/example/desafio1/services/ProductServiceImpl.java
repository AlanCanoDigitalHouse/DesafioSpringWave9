package com.example.desafio1.services;

import com.example.desafio1.dto.Post;
import com.example.desafio1.dto.User;
import com.example.desafio1.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void addNewPost(Post post) {
        productRepository.addNewPost(post);
    }

    @Override
    public List<Post> listLastFollowedPosts(List<User> vendors) {
        return productRepository.listFollowedLastPosts(vendors);
    }
}
