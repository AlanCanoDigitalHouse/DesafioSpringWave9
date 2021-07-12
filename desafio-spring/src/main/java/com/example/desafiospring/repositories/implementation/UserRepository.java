package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.User;
import com.example.desafiospring.repositories.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private static List<User> users = new ArrayList<>();

    public UserRepository(List<User> users) {
        this.users.add(new User(1L, "Superman", true));
        this.users.add(new User(2L, "Batman", true));
        this.users.add(new User(3L, "Spider-man", false));
        this.users.add(new User(4L, "Wonder woman", false));
        this.users.add(new User(5L, "Poseidon", false));
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User findById(Long userId, boolean isSeller) {
        Optional<User> user = users.stream()
                .filter(x -> x.getUserId().equals(userId) && x.isSeller() == isSeller).findFirst();
        if (user.isEmpty())
            return null;
        return user.get();
    }

}
