package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.PostException;
import com.example.desafiospring.models.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {

    @Override
    public Post findById(Long id) throws PostException {
        List<Post> posts = this.loadDB();
        Optional<Post> result = posts.stream().filter(p -> p.getIdPost().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new PostException(PostException.POST_NOT_EXISTS + id);
    }

    @Override
    public Collection<Post> findByIds(Collection<Long> ids) {
        List<Post> posts = this.loadDB();
        return posts.stream().filter(s -> ids.contains(s.getUserId())).collect(Collectors.toList());
    }

    private List<Post> loadDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/posts.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private List<Post> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<Post>> typeReference = new TypeReference<>() {
        };
        List<Post> posts = null;
        try {
            posts = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private List<Post> saveToFile(List<Post> posts) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(posts);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Long addPost(Post post) throws PostException {
        List<Post> posts = this.loadDB();
        Optional<Long> maxIdPost = posts.stream().max(Comparator.comparing(Post::getIdPost)).map(p -> p.getIdPost());
        Long idPost;
        if (maxIdPost.isEmpty())
            idPost = 1L;
        else
            idPost = maxIdPost.get() + 1;
        post.setIdPost(idPost);
        posts.add(post);
        this.saveToFile(posts);
        return idPost;
    }

    @Override
    public List<Post> findPromoPostsBySellerId(Long sellerId) {
        List<Post> posts = this.loadDB();
        return posts.stream().filter(p -> p.getUserId().equals(sellerId))
                .filter(p -> p.getHasPromo() != null)
                .filter(p -> p.getHasPromo().equals(true)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellerId(Long sellerId) {
        List<Post> posts = this.loadDB();
        return posts.stream().filter(p -> p.getUserId().equals(sellerId)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findBySellersIds(Collection<Long> sellersIds) {
        List<Post> posts = this.loadDB();
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostAfterDateBySellersIds(Collection<Long> sellersIds, Date date) {
        List<Post> posts = this.loadDB();
        return posts.stream().filter(p -> sellersIds.contains(p.getUserId())).filter(p -> p.getDate().after(date)).collect(Collectors.toList());
    }

}
