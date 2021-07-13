package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.Post;
import com.example.desafiospring.repositories.IPostRepository;
import com.example.desafiospring.utils.Utils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {

    private static List<Post> posts;
    private static Long id;

    public PostRepository() {
        posts = new ArrayList<>();
        id = 0L;
    }

    @Override
    public Post createPost(Post post) {
        post.setId_post(++id);
        posts.add(post);
        return post;
    }

    @Override
    public List<Post> getPostsByUsersId(List<Long> usersId) {
        List<Post> respond = new ArrayList<>();
        if (!usersId.isEmpty())
            respond = posts.stream().filter(x ->
                usersId.stream().anyMatch(y -> y.equals(x.getUserId())) &&
                        Utils.dateInRange(x.getDate())).collect(Collectors.toList());
        return respond;
    }
}
