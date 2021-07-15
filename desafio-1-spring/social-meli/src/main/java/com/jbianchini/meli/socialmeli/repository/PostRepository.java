package com.jbianchini.meli.socialmeli.repository;

import com.jbianchini.meli.socialmeli.model.Post;
import com.jbianchini.meli.socialmeli.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {
    private final Map<Integer, Post> database = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getPostId() == null) {
            post.setPostId(database.values().size());
        }
        database.put(post.getPostId(), post);
        return post;
    }

    @Override
    public Post findByPostId(Integer postId) {
        return database.get(postId);
    }

    @Override
    public void delete(Post post) {
        database.remove(post.getPostId());
    }

    @Override
    public List<Post> findByUserSinceTwoWeeksAgo(User user) {
        LocalDate greaterThan = LocalDate.now().minusDays(14);
        return this.database.values().stream().filter(p -> p.getDate().isAfter(greaterThan) && user.equals(p.getUser()))
                .collect(Collectors.toList());
    }

}
