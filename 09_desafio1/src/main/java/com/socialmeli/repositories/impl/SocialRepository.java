package com.socialmeli.repositories.impl;

import com.socialmeli.exceptions.Found.UserNotFoundException;
import com.socialmeli.models.UserSocial;
import com.socialmeli.repositories.ISocialRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class SocialRepository implements ISocialRepository {

    private final Map<Integer, UserSocial> usersDatabase;

    public SocialRepository() {

        this.usersDatabase = new HashMap<>();
        this.saveUser(new UserSocial(0, "Logitech"));
        this.saveUser(new UserSocial(1, "Josean-py"));
    }

    @Override
    public UserSocial findUserbyId(Integer sellerId) {
        UserSocial sellerFind = usersDatabase.get(sellerId);
        if (Objects.isNull(sellerFind))
            throw new UserNotFoundException();
        return sellerFind;
    }

    @Override
    public UserSocial saveUser(UserSocial user) {
        if (Objects.isNull(user.getId()))
            user.setId(usersDatabase.values().size());
        usersDatabase.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(UserSocial user) {
        usersDatabase.remove(user.getId());
    }


}
