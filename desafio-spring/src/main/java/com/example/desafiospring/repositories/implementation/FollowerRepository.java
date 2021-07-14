package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.Follower;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.repositories.IFollowerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository
public class FollowerRepository implements IFollowerRepository {

    private final ObjectMapper mapper;

    public FollowerRepository() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Long getNumFollowersById(Long userId) throws IOException {
        AtomicReference<Long> count = new AtomicReference<>(0L);
        this.loadDataBase().forEach(x -> {
            if (x.getUserIdFollowed().equals(userId))
                count.set(count.get() + 1);
        });
        return count.get();
    }

    @Override
    public void followUserById(Long userId, Long userIdToFollow) throws IOException {
        List<Follower> followers = this.loadDataBase();
        followers.add(new Follower(userId, userIdToFollow));
        this.writeDataBase(followers);
    }

    @Override
    public List<Long> getListFollowersById(Long userId) throws IOException {
        List<Long> response = new ArrayList<>();
        this.loadDataBase().forEach(x -> {
            if (x.getUserIdFollowed().equals(userId))
                response.add(x.getUserId());
        });
        return response;
    }

    @Override
    public List<Long> getListFollowedById(Long userId) throws IOException {
        List<Long> response = new ArrayList<>();
        this.loadDataBase().forEach(x -> {
            if (x.getUserId().equals(userId))
                response.add(x.getUserIdFollowed());
        });
        return response;
    }

    @Override
    public void unfollowUserById(Long userId, Long userIdToUnfollow) throws IOException {
        this.writeDataBase(
                this.loadDataBase().stream()
                        .filter(x -> !(x.getUserId().equals(userId) && x.getUserIdFollowed().equals(userIdToUnfollow)))
                        .collect(Collectors.toList()));
    }

    @Override
    public boolean isFollowedByUserId(Long userId, Long userIdToFollow) throws IOException {
        Optional<Follower> filter = this.loadDataBase().stream()
                .filter(x -> x.getUserId().equals(userId) && x.getUserIdFollowed().equals(userIdToFollow))
                .findFirst();
        return filter.isPresent();
    }

    private void writeDataBase(List<Follower> followers) throws IOException {
        mapper.writeValue(new File(ConstantEnum.JSON_FOLLOWERS), followers);
    }

    private List<Follower> loadDataBase() throws IOException {
        return mapObject(Paths.get(ConstantEnum.JSON_FOLLOWERS).toFile());
    }

    private List<Follower> mapObject(File file) throws IOException {
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (MismatchedInputException e) {
            return new ArrayList<>();
        }
    }
}
