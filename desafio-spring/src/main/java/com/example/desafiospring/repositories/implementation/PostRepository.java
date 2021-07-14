package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.Post;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.repositories.IPostRepository;
import com.example.desafiospring.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository {

    private final ObjectMapper mapper;

    public PostRepository() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Post createPost(Post post) throws IOException {
        List<Post> db = this.loadDataBase();
        post.setId_post(this.getLastId(db) + 1);
        db.add(post);
        this.writeDataBase(db);
        return post;
    }

    @Override
    public List<Post> getPostsByUsersId(List<Long> usersId) throws IOException {
        List<Post> respond = new ArrayList<>();
        if (!usersId.isEmpty())
            respond = this.loadDataBase().stream().filter(x ->
                usersId.stream().anyMatch(y -> y.equals(x.getUserId())) &&
                        Utils.dateInRange(x.getDate())).collect(Collectors.toList());
        return respond;
    }

    @Override
    public Long countPostsByUserId(Long userId, boolean hasPromo) throws IOException {
        AtomicReference<Long> count = new AtomicReference<>(0L);
        this.loadDataBase().forEach(x -> {
            if (x.getUserId().equals(userId) &&
                    (!hasPromo || (Objects.nonNull(x.getHasPromo()) && x.getHasPromo())))
                count.set(count.get() + 1);
        });
        return count.get();
    }

    @Override
    public List<Post> getPostsByUserId(Long userId, boolean hasPromo) throws IOException {
        List<Post> response;
        response = this.loadDataBase().stream()
                .filter(x -> x.getUserId().equals(userId) &&
                        (!hasPromo || (Objects.nonNull(x.getHasPromo()) && x.getHasPromo())))
                .collect(Collectors.toList());
        return response;
    }

    private void writeDataBase(List<Post> posts) throws IOException {
        mapper.writeValue(new File(ConstantEnum.JSON_POSTS), posts);
    }

    private List<Post> loadDataBase() throws IOException {
        return mapObject(Paths.get(ConstantEnum.JSON_POSTS).toFile());
    }

    private List<Post> mapObject(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }

    private Long getLastId(List<Post> posts) {
        AtomicReference<Long> id = new AtomicReference<>(0L);
        posts.forEach(x -> {
            if (x.getId_post().compareTo(id.get()) > 0)
                id.set(x.getId_post());
        });
        return id.get();
    }
}
