package com.mercadolibre.desafio.persistence.impl;

import com.mercadolibre.desafio.dtos.ResponseCountFollowers;
import com.mercadolibre.desafio.dtos.ResponseFollowed;
import com.mercadolibre.desafio.dtos.ResponseFollowers;
import com.mercadolibre.desafio.dtos.ResponseUser;
import com.mercadolibre.desafio.exception.UserException;
import com.mercadolibre.desafio.model.User;
import com.mercadolibre.desafio.persistence.UserPersistence;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

@Repository
public class UserInMemoryPersistence implements UserPersistence {

    Map<Integer, User> database = new HashMap<>();
    private final static Logger LOGGER = Logger.getLogger(UserInMemoryPersistence.class.getName());

    public UserInMemoryPersistence() {

        User user0 = new User(0, "juan", new ArrayList<>(Arrays.asList(1)), new ArrayList<>(Arrays.asList(1)),new ArrayList<>());
        User user1 = new User(1, "carlos", new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0)),new ArrayList<>());
        User user2 = new User(2, "pedro", new ArrayList<>(), new ArrayList<>(),new ArrayList<>());
        database.put(0, user0);
        database.put(1, user1);
        database.put(2, user2);

    }

    @Override
    public void follow(Integer userID, Integer userToFollowId) throws UserException {
        User user = getUserById(userID);
        User userToFollow = getUserById(userToFollowId);
        if (user.getFollowed().contains(userToFollowId)) {
            throw new UserException(UserException.USER_ALREADY_FOLLOWED);
        }
        user.getFollowed().add(userToFollow.getUserID());
        userToFollow.getFollowers().add(user.getUserID());
    }

    @Override
    public ResponseCountFollowers countFollowers(Integer userId) throws UserException {
        User user = getUserById(userId);
        Integer count = user.getFollowers().size();

        return new ResponseCountFollowers(user.getUserID(), user.getUserName(), count);
    }

    @Override
    public ResponseFollowers getFollowers(Integer userId,String order) throws UserException {
        User user = getUserById(userId);
        List<ResponseUser> followers = new ArrayList<>();
        user.getFollowers().forEach(throwingConsumerWrapper(i->followers.add(new ResponseUser(getUserById(i)))));
        order(followers,order);
        return new ResponseFollowers(user.getUserID(), user.getUserName(), followers);
    }

    @Override
    public ResponseFollowed getFollowed(Integer userId, String order) throws UserException {
        User user = getUserById(userId);
        List<ResponseUser> followed = new ArrayList<>();
        user.getFollowed().forEach(throwingConsumerWrapper(i->followed.add(new ResponseUser(getUserById(i)))));
        order(followed,order);
        return new ResponseFollowed(user.getUserID(), user.getUserName(), followed);
    }

    public void order(List<ResponseUser> userList,String order){
        if(order.equals("name_asc")){
            userList.sort(Comparator.comparing(ResponseUser::getUserName));
        }
        else if(order.equals("name_desc")){
            userList.sort(Comparator.comparing(ResponseUser::getUserName).reversed());
        }
    }

    @Override
    public void unfollow(Integer userId, Integer userToUnfollow) throws UserException {
        User user = getUserById(userId);
        User userUnfollow = getUserById(userToUnfollow);
        if (!user.getFollowed().contains(userToUnfollow)) {
            throw new UserException(UserException.USER_NOT_ALREADY_FOLLOWED);
        }
        user.getFollowed().remove(userToUnfollow);
        userUnfollow.getFollowers().remove(userId);
    }

    @Override
    public User getUserById(Integer userId) throws UserException {
        User user = database.get(userId);
        return Optional.ofNullable(user).orElseThrow(() -> new UserException(UserException.ID_NOT_FOUND));
    }


    static <T> Consumer<T> throwingConsumerWrapper(
            ThrowingConsumer<T> throwingConsumer) {
        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Throwable ex) {
                LOGGER.info("Got an exception. " + ex.getMessage());
            }
        };
    }
}
