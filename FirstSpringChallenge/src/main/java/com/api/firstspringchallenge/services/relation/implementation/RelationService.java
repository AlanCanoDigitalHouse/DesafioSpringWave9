package com.api.firstspringchallenge.services.relation.implementation;

import com.api.firstspringchallenge.exceptions.DuplicatedFollowException;
import com.api.firstspringchallenge.exceptions.UnableToFollowYourselfException;
import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;
import com.api.firstspringchallenge.repositories.relation.implementation.RelationsRepository;
import com.api.firstspringchallenge.services.relation.RelationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RelationService implements RelationServiceI {

    @Autowired
    private RelationsRepository repository;

    @Override
    public void createRelation(User oneUser, User otherUser) {
        if(!Objects.isNull(findRelation(oneUser,otherUser))){
            throw new DuplicatedFollowException("user id "+ oneUser.getUserId() + " has already followed user id " + otherUser.getUserId());
        }
        if(oneUser==otherUser){
            throw new UnableToFollowYourselfException("cannot follow the same user id");
        }

        Relation relation = new Relation(oneUser, otherUser);
        repository.addRelation(relation);
    }

    @Override
    public void deleteRelation(Relation relation) {
        repository.removeRelation(relation);
    }

    @Override
    public List<User> getFollowers(User user) {
        return repository.getFollowers(user);
    }

    @Override
    public List<User> getFollowed(User user) {
        return repository.getFollowed(user);
    }

    @Override
    public List<Seller> getSellers(User user) {
        return repository.getSellers(user);
    }

    @Override
    public Relation findRelation(User user, User otherUser) {
        return repository.findRelation(user, otherUser);
    }

}
