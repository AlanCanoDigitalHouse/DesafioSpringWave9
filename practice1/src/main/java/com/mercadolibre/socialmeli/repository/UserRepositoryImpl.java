package com.mercadolibre.socialmeli.repository;

import com.mercadolibre.socialmeli.dto.FollowDTO;
import com.mercadolibre.socialmeli.dto.UserDTO;
import com.mercadolibre.socialmeli.exception.EntityException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, UserDTO> users = new HashMap<>();
    private final List<FollowDTO> follows = new ArrayList<>();

    public UserRepositoryImpl() {
        this.loadDatabase();
    }

    private void loadDatabase() {
        this.loadUsers();
        this.loadFollows();
    }

    private void loadUsers() {
        users.put(0, new UserDTO(0, "user1"));
        users.put(1, new UserDTO(1, "vendor1"));
        users.put(2, new UserDTO(2, "Luis"));
        users.put(3, new UserDTO(3, "Simon"));
        users.put(4, new UserDTO(4, "Carla"));
    }

    private void loadFollows() {
        // User 0 follows all users
        follows.add(new FollowDTO(0, 1));
        follows.add(new FollowDTO(0, 2));
        follows.add(new FollowDTO(0, 3));
        follows.add(new FollowDTO(0, 4));

        // All follow user with id 1
        follows.add(new FollowDTO(2, 1));
        follows.add(new FollowDTO(3, 1));
        follows.add(new FollowDTO(4, 1));
    }

    @Override
    public UserDTO save(UserDTO user) {
        if (user.getUserID() == 0)
            user.setUserID(users.values().size());
        users.put(user.getUserID(), user);
        return user;
    }

    @Override
    public FollowDTO saveFollow(FollowDTO follow) throws EntityException {
        Optional<FollowDTO> currentFollow = follows.stream().filter(followDTO -> followDTO.equals(follow)).findAny();
        if (currentFollow.isPresent())
            throw new EntityException("User Already followed");
        if (!users.containsKey(follow.getFollower()) || !users.containsKey(follow.getFollowed()))
            throw new EntityException("One of both users not found");
        follows.add(follow);
        return follow;
    }

    @Override
    public void deleteFollow(FollowDTO followDTO) {
        follows.remove(followDTO);
    }

    @Override
    public Optional<FollowDTO> findFollow(Integer followerId, Integer followedId) {
        return follows.stream().
                filter(followDTO ->
                        followDTO.getFollower().equals(followerId) && followDTO.getFollowed().equals(followedId))
                .findFirst();
    }

    @Override
    public Optional<UserDTO> findUserByUserId(Integer userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<UserDTO> findUserFollowers(Integer userId) {
        List<UserDTO> followers = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (FollowDTO follow :
                follows) {
            if (follow.getFollowed().equals(userId))
                tempList.add(follow.getFollower());
        }

        for (UserDTO user : users.values()) {
            for (Integer i :
                    tempList) {
                if (user.getUserID().equals(i))
                    followers.add(user);
            }
        }
        return followers;
    }

    @Override
    public List<UserDTO> findUserFollowed(Integer userId) {
        List<UserDTO> followed = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (FollowDTO follow :
                follows) {
            if (follow.getFollower().equals(userId))
                tempList.add(follow.getFollowed());
        }

        for (UserDTO user : users.values()) {
            for (Integer i :
                    tempList) {
                if (user.getUserID().equals(i))
                    followed.add(user);
            }
        }
        return followed;
    }

    @Override
    public void delete(UserDTO userDTO) {

    }
}
