package com.mercado_libre.bootcamp.spring.desafio.services.relation;

import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;

import java.util.List;

public interface RelationService {

    public void addFollower(Seller seller, User user);

    public void unfollowSeller(Seller seller, User user);

    public List<User> getFollowers(Seller seller);

    public List<Seller> getFollowedSellers(User user);
}
