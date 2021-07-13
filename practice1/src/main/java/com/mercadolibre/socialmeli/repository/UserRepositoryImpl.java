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
        users.put(2, new UserDTO(2, "user2"));
        users.put(3, new UserDTO(3, "vendor2"));
        users.put(4, new UserDTO(4, "vendor3"));
    }

    private void loadFollows() {
        follows.add(new FollowDTO(0, 1));
        follows.add(new FollowDTO(0, 2));
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
            throw new EntityException("No follower or followed user was found");
        follows.add(follow);
        return follow;
    }

    @Override
    public Optional<UserDTO> findUserByUserId(Integer userId) {
        return Optional.of(users.get(userId));
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
    public List<UserDTO> findFollowedByUser(Integer userId) {
        return null;
    }

    @Override
    public void delete(UserDTO linkDTO) {

    }
}
