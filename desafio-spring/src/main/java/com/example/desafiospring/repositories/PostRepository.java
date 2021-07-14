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
    public Post findById(Long id) throws PostException {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        Optional<Post> result = posts.stream().filter(p -> p.getIdPost().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new PostException(PostException.POST_NOT_EXISTS + id);
    }

    @Override
    public Collection<Post> findByIds(Collection<Long> ids) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(s -> ids.contains(s.getUserId())).collect(Collectors.toList());
    }

    @Override
    public Long addPost(Post post) throws PostException {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        Optional<Long> maxIdPost = posts.stream().max(Comparator.comparing(Post::getIdPost)).map(p -> p.getIdPost());
        Long idPost;
        if (maxIdPost.isEmpty())
            idPost = 1L;
        else
            idPost = maxIdPost.get() + 1;
        post.setIdPost(idPost);
        posts.add(post);
        this.saveToFile(FILENAME, posts);
        return idPost;
    }

    @Override
    public List<Post> findPromoPostsBySellerId(Long sellerId) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> p.getUserId().equals(sellerId))
                .filter(p -> p.getHasPromo() != null)
                .filter(p -> p.getHasPromo().equals(true)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellerId(Long sellerId) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> p.getUserId().equals(sellerId)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellersIds(Collection<Long> sellersIds) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostAfterDateBySellersIds(Collection<Long> sellersIds, Date date) {
        List<Post> posts = this.loadDB(FILENAME, Post.class);
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).filter(p -> p.getDate().after(date)).collect(Collectors.toList());
    }

}
