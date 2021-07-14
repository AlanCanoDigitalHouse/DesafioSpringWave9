package com.desafiospring.socialmeli.repositories;

import com.desafiospring.socialmeli.dtos.models.Post;
import com.desafiospring.socialmeli.dtos.requests.PostRequestDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IRepository<Post> {

    private List<Post> posts;

    public ProductRepository(){
        posts = new ArrayList<>();
    }

    @Override
    public Post add(Post item) {
        this.posts.add(item);
        return item;
    }

    @Override
    public Post get(int itemId) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public Post update(Post item) {
        return null;
    }

    @Override
    public void delete(int itemId) {

    }
}
