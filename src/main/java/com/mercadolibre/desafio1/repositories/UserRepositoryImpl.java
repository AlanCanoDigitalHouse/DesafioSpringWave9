package com.mercadolibre.desafio1.repositories;

import com.mercadolibre.desafio1.dto.UserDTO;
import com.mercadolibre.desafio1.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("UserRepository")
public class UserRepositoryImpl implements UserRepository {
    private Map<Integer, UserDTO> users;
    private static final AtomicInteger userIdCount = new AtomicInteger(0);

    @Override
    public void initBdd(Map<Integer, UserDTO> usersNew) {
        this.users = usersNew;
        this.userIdCount.addAndGet(usersNew.size());
    }

    @Override
    public UserDTO saveUser(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        UserDTO result = null;
        if(this.users.containsKey(userId)){
            result = this.users.get(userId);
        }
        return result;
    }

    @Override
    public ArrayList<Integer> getFollowsById(Integer userId) {
        return this.getUserById(userId).getFollows();
    }
}
