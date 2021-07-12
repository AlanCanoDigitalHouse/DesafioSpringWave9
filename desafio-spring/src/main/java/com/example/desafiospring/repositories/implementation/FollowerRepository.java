package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.repositories.IFollowerRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class FollowerRepository implements IFollowerRepository {

    private static Map<Long, List<Long>> followers = new HashMap<>();


    @Override
    public Long getNumFollowersById(Long userId) {
        AtomicReference<Long> contador = new AtomicReference<>(0L);
        followers.entrySet().forEach(x ->
                x.getValue().forEach(y -> {
                        if (y.equals(userId))
                            contador.set(contador.get() + 1);
                }));
        return contador.get();
    }

    @Override
    public void followUserById(Long userId, Long userIdToFollow) {
        List<Long> aux = followers.get(userId);
        if (Objects.nonNull(aux)) {
            aux.add(userIdToFollow);
        } else {
            aux = new ArrayList<>();
            aux.add(userIdToFollow);
        }
        followers.put(userId, aux);
    }

    @Override
    public List<Long> getListFollowersById(Long userId) {
        return null;
    }

    @Override
    public List<Long> getListFollowedById(Long userId) {
        return null;
    }

    @Override
    public void unfollowUserById(Long userId, Long userIdToUnfollow) {

    }

    @Override
    public boolean isFollowedByUserId(Long userId, Long userIdToFollow) {
        List<Long> followed = followers.get(userId);
        if (!Objects.nonNull(followed))
            return false;
        Optional<Long> isFollowed = followed.stream().filter(x -> x.equals(userIdToFollow)).findFirst();
        return isFollowed.isPresent();
    }

    public Map<Long, List<Long>> getAllFollowers() {
        return followers;
    }
}
