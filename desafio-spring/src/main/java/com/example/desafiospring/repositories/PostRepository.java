package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.models.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {

    public static final String FILENAME = "posts.json";

    @Override
    public Post findById(Integer id) throws PostException {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        Optional<Post> result = posts.stream().filter(p -> p.getIdPost().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new PostException(PostException.POST_NOT_EXISTS + id);
    }

    @Override
    public Collection<Post> findByIds(Collection<Integer> ids) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(s -> ids.contains(s.getUserId())).collect(Collectors.toList());
    }

    @Override
    public Integer addPost(Post post) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        Optional<Integer> maxIdPost = posts.stream().max(Comparator.comparing(Post::getIdPost)).map(p -> p.getIdPost());
        Integer idPost;
        if (maxIdPost.isEmpty())
            idPost = 1;
        else
            idPost = maxIdPost.get() + 1;
        post.setIdPost(idPost);
        posts.add(post);
        this.saveToFile(FILENAME, posts);
        return idPost;
    }

    @Override
    public List<Post> findPromoPostsBySellerId(Integer sellerId) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> p.getUserId().equals(sellerId))
                .filter(p -> p.getHasPromo() != null)
                .filter(p -> p.getHasPromo().equals(true)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellerId(Integer sellerId) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> p.getUserId().equals(sellerId)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellersIds(Collection<Integer> sellersIds) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostAfterDateBySellersIds(Collection<Integer> sellersIds, Date date) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).filter(p -> p.getDate().after(date)).collect(Collectors.toList());
    }

}
