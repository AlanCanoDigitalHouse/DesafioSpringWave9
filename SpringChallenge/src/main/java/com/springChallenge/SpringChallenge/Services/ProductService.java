package com.springChallenge.SpringChallenge.Services;

import com.springChallenge.SpringChallenge.Dtos.Post;
import com.springChallenge.SpringChallenge.Repositories.IRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private final IRepository repository;

    public ProductService(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addPost(Post post) {
        if(!repository.existUser(post.getUserId())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User not found"
            );
        }
        repository.addProduct(post);
    }

    @Override
    public List<Post> followedPost(int userId, String sort) {
        var result = repository.followedPost(userId, sort);
        if(result == null || result.size() == 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Not post to view"
            );
        }
        return result;
    }
}
