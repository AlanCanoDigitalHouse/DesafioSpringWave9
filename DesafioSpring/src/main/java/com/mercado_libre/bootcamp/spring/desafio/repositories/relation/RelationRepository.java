package com.mercado_libre.bootcamp.spring.desafio.repositories.relation;

import com.mercado_libre.bootcamp.spring.desafio.models.Relation;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;

import java.util.List;

public interface RelationRepository {

    public void addRelation(Relation relation);

    public void removeRelation(Relation relation);

    public List<User> getFollowers(int sellerId);

    public List<Seller> getFollowedSellers(int followerId);
}
