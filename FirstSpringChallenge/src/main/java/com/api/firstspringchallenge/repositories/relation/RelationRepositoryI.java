package com.api.firstspringchallenge.repositories.relation;

import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;

import java.util.List;

public interface RelationRepositoryI {

    void addRelation(Relation relation);

    void removeRelation(Relation relation);

    List<User> getFollowers(User user);

    List<User> getFollowed(User user);

    Relation findRelation(User user, User otherUser);

    List<Seller> getSellers(User user);

    boolean doesntContains(Relation relation);

}
