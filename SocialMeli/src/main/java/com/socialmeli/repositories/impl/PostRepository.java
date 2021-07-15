package com.socialmeli.repositories.impl;

import com.socialmeli.exceptions.Found.PostNotFoundException;
import com.socialmeli.models.PostSocial;
import com.socialmeli.models.ProductSocial;
import com.socialmeli.repositories.IPostRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {

    private final Map<Integer, PostSocial> postsDatabase;

    public PostRepository() {

        this.postsDatabase = new HashMap<>();
        ProductSocial product = new ProductSocial(
                0, "Mouse",
                "Gamer", "Logitech",
                "White", "KDA Edition");
        this.savePost(new PostSocial(
                0, 0,
                LocalDate.now().minus(Period.ofDays(10)), product,
                100, 129200.0));
        product = new ProductSocial(
                0, "Keyboard",
                "Gamer", "Logitech",
                "White - Black", "KDA Edition");
        this.savePost(new PostSocial(
                0, 1,
                LocalDate.now(), product,
                80, 694200.0));
    }

    @Override
    public PostSocial findById(Integer Id) {
        PostSocial postFind = postsDatabase.get(Id);
        if (Objects.isNull(postFind))
            throw new PostNotFoundException();
        return postFind;
    }

    @Override
    public PostSocial savePost(PostSocial post) {
        if (Objects.isNull(post.getId()))
            post.setId(postsDatabase.values().size());

        postsDatabase.put(post.getId(), post);
        return post;
    }

    @Override
    public void delete(PostSocial post) {
        postsDatabase.remove(post.getId());
    }

    @Override
    public ArrayList<PostSocial> filterByDate(Integer daysAgo) {
        return postsDatabase.values().stream().filter(post ->
                post.getDate().isAfter(LocalDate.now().minus(Period.ofDays(daysAgo)))
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<PostSocial> list() {
        return new ArrayList<>(postsDatabase.values());
    }
}
