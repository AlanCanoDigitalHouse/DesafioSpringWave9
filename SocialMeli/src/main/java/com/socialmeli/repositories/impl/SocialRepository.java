package com.socialmeli.repositories.impl;

import com.socialmeli.exceptions.UserNotFoundException;
import com.socialmeli.models.User;
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
        this.usersDatabase.put(0,new UserSocial(0, "Logitech"));
        this.usersDatabase.put(1,new UserSocial(1, "Josean-py"));
    }

    @Override
    public UserSocial findUserbyId(Integer sellerId) {
        UserSocial sellerFind = usersDatabase.get(sellerId);
        if(Objects.isNull(sellerFind))
            throw new UserNotFoundException();
        return sellerFind;
    }

    @Override
    public UserSocial saveUser(UserSocial user) {
        if (user.getId() == null)
            user.setId(usersDatabase.values().size());
        usersDatabase.put(user.getId(), user);
        return user;
    }




}
