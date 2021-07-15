package com.desafio.demo.repositories.implementations;

import com.desafio.demo.exceptions.InvalidIdUserException;
import com.desafio.demo.entiity.User;
import com.desafio.demo.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private HashMap<Integer, User> users;

    @Override
    public void saveFollow(Integer userId, Integer userIdToFollow) {
        getUser(userId).addFollowed(userIdToFollow);
        getUser(userIdToFollow).addFollower(userId);
    }

    @Override
    public void deleteFollow(int userId, int userIdToUnfollow) {
      getUser(userId).deleteFollowed(userIdToUnfollow);
      getUser(userIdToUnfollow).deleteFollower(userId);
    }

    @Override
    public User getUser(Integer userId) {
        User foundUser = users.get(userId);
        if (foundUser == null) {
            throw new InvalidIdUserException();
        }
        return foundUser;
    }

    @Override
    public void initilize() {
        users = new HashMap<>();
        String[] usersName = {
                "auser1", "zuser2", "buser3", "guser4", "xuser5", "duser6", "ruser7",
                "tuser8", "huser9", "euser10", "user11", "user12", "user13", "user14",
                "user15", "user16", "user17", "user18"};
                boolean init = false;
        if (!init) {
            for (int i = 0; i < 18; i++) {
                users.put(i, new User(usersName[i]));
                init = true;
            }
            System.out.println(users);
        }
    }
}
