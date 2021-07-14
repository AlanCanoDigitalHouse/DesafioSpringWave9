package com.example.desafiospring.repositories;

import com.example.desafiospring.exceptions.UserException;
import com.example.desafiospring.models.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository {

    public static final String FILENAME = "users.json";

    @Override
    public User findById(Integer id) throws UserException {
        List<User> users = this.loadDB(FILENAME, User.class);
        Optional<User> result = users.stream().filter(u -> u.getUserId().equals(id)).findAny();
        if (result.isPresent())
            return result.get();
        else
            throw new UserException(UserException.USER_NOT_EXISTS + id);
    }

    @Override
    public Collection<User> findByIds(Collection<Integer> ids) {
        List<User> users = this.loadDB(FILENAME, User.class);
        return users.stream().filter(u -> ids.contains(u.getUserId())).collect(Collectors.toList());
    }

    @Override
    public Boolean checkIfUserExistsById(Integer userId) throws UserException {
        List<User> users = this.loadDB(FILENAME, User.class);
        boolean result = users.stream().anyMatch(u -> u.getUserId().equals(userId));
        if (result)
            return true;
        else
            throw new UserException(UserException.USER_NOT_EXISTS + userId);
    }
}
