package com.mercadolibre.social_meli.repository;

import com.mercadolibre.social_meli.entity.User;
import com.mercadolibre.social_meli.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends JSONRepository<User> implements IUserRepository {

    private static final String USERS_DIR = "classpath:static/users.json";

    public UserRepository() {
        super(USERS_DIR, User.class);
    }

    @Override
    public User getUser(Integer userId) {
        var users = getUsers();
        var requestedUser = users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();

        if (requestedUser.isPresent()) {
            return requestedUser.get();
        } else {
            throw new ResourceNotFoundException("Requested user does not exist.");
        }
    }

    @Override
    public List<User> getUsers() {
        return getData();
    }

    @Override
    public void updateAllUsers(List<User> users) {
        writeDatabase(users);
    }

}
