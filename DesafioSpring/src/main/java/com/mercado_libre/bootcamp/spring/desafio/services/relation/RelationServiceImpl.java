package com.mercado_libre.bootcamp.spring.desafio.services.relation;

import com.mercado_libre.bootcamp.spring.desafio.models.Relation;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import com.mercado_libre.bootcamp.spring.desafio.repositories.relation.RelationRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelationServiceImpl implements RelationService {

    private final RelationRepositoryImpl relationRepository;

    @Override
    public void addFollower(Seller seller, User user) {

        Relation relation = new Relation(seller, user);

        relationRepository.addRelation(relation);
    }

    @Override
    public void unfollowSeller(Seller seller, User user) {

        Relation relation = new Relation(seller, user);

        relationRepository.removeRelation(relation);
    }

    @Override
    public List<User> getFollowers(Seller seller) {
        return relationRepository.getFollowers(seller.getUserId());
    }

    @Override
    public List<Seller> getFollowedSellers(User user) {
        return relationRepository.getFollowedSellers(user.getUserId());
    }

}
