package com.api.firstspringchallenge.services.relation;

import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;

import java.util.List;

public interface RelationServiceI {
    void createRelation(User oneUser, User otherUser);

    void deleteRelation(Relation relation);

    List<User> getFollowers(User user);

    List<User> getFollowed(User user);

    List<Seller> getSellers(User user);

    Relation findRelation(User user, User otherUser);


}
