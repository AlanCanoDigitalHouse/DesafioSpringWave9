package com.example.socialmeli.repositories.implementations;

import com.example.socialmeli.domains.User;
import com.example.socialmeli.exceptions.DataNotFound;
import com.example.socialmeli.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Marcos"));
        users.add(new User(2, "Martin"));
        users.add(new User(3, "Matias"));
        users.add(new User(4, "Victoria"));
    }

    @Override
    public User saveUser(User user) {
        if (Objects.isNull(user.getUserId())) {
            user.setUserId(users.size() + 1);
            users.add(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByUserId(Integer userId) throws DataNotFound {
        Optional<User> userDTO = users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (userDTO.isPresent()) {
            return userDTO.get();
        }
        throw new DataNotFound("Usuario con userId: " + userId + " no existe ");
    }

    @Override
    public User findByUserName(String userName) throws DataNotFound {
        Optional<User> userDTO = users.stream().filter(u -> u.getUserName().equalsIgnoreCase(userName)).findFirst();
        if (userDTO.isPresent()) {
            return userDTO.get();
        }
        throw new DataNotFound("Usuario con userName: " + userName + " no existe ");
    }

}
