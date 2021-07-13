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
        AtomicReference<Long> count = new AtomicReference<>(0L);
        followers.forEach((key, value) -> value.forEach(y -> {
            if (y.equals(userId))
                count.set(count.get() + 1);
        }));
        return count.get();
    }

    @Override
    public void followUserById(Long userId, Long userIdToFollow) {
        List<Long> aux = followers.get(userId);
        if (!Objects.nonNull(aux)) {
            aux = new ArrayList<>();
        }
        aux.add(userIdToFollow);
        followers.put(userId, aux);
    }

    @Override
    public List<Long> getListFollowersById(Long userId) {
        List<Long> response = new ArrayList<>();
        followers.forEach((key, value) -> value.forEach(y -> {
            if (y.equals(userId))
                response.add(key);
        }));
        return response;
    }

    @Override
    public List<Long> getListFollowedById(Long userId) {
        return followers.get(userId);
    }

    @Override
    public void unfollowUserById(Long userId, Long userIdToUnfollow) {
        followers.get(userId).remove(userIdToUnfollow);
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
