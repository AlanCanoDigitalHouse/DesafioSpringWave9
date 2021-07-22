package com.api.firstspringchallenge.repositories.relation.implementation;

import com.api.firstspringchallenge.exceptions.UnexistingRelationException;
import com.api.firstspringchallenge.models.Relation;
import com.api.firstspringchallenge.models.Seller;
import com.api.firstspringchallenge.models.User;
import com.api.firstspringchallenge.repositories.relation.RelationRepositoryI;
import com.api.firstspringchallenge.utils.FileUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RelationsRepository implements RelationRepositoryI {

    private List<Relation> relations;

    @PostConstruct
    public void loadUsers() {
        Type listType = new TypeToken<List<Seller>>() {
        }.getType();
        String fileName = "static/json/relations.json";
        try {
            this.relations = FileUtils.getDataFrom(fileName, listType);
        } catch (IOException e) {
            this.relations = new ArrayList<>();
        }
    }

    @Override
    public void addRelation(Relation relation) {
        this.relations.add(relation);
    }

    @Override
    public void removeRelation(Relation relation) {
        if(Objects.isNull(relation))
            throw new UnexistingRelationException("cannot unfollow because there is no relation between the users");
        this.relations.remove(relation);
    }

    @Override
    public List<User> getFollowers(User user) {
        List<Relation> followers = this.relations.stream().filter(r -> r.getFollowed() == user).collect(Collectors.toList());

        return followers.stream().map(r -> r.getFollower()).collect(Collectors.toList());

    }

    @Override
    public List<User> getFollowed(User user) {
        List<Relation> followed = this.relations.stream().filter(r -> r.getFollower() == user).collect(Collectors.toList());
        return followed.stream().map(r -> r.getFollowed()).collect(Collectors.toList());

    }

    @Override
    public Relation findRelation(User user, User otherUser) {
        return relations.stream().filter(r -> r.getFollower() == user && r.getFollowed() == otherUser).findAny().orElse(null);
    }

    @Override
    public List<Seller> getSellers(User user) {
        List<Seller> followed = this.relations.stream().filter(r -> r.getFollower() == user).map(f -> (Seller) f.getFollowed()).collect(Collectors.toList());
        return followed;
    }

    @Override
    public boolean doesntContains(Relation relation){
        return !relations.contains(relation);
    }


}
