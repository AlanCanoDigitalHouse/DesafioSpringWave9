package com.mercado_libre.bootcamp.spring.desafio.repositories.relation;

import com.mercado_libre.bootcamp.spring.desafio.models.Relation;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RelationRepositoryImpl implements RelationRepository {

    @Getter
    private List<Relation> relations;

    @Override
    public void addRelation(Relation newRelation) {

        if (!relations.contains(newRelation)) {
            relations.add(newRelation);
        }
    }

    @Override
    public void removeRelation(Relation relation) {
        relations.remove(relation);
    }

    @Override
    public List<User> getFollowers(int sellerId) {
        return relations.stream()
                .filter(x -> x.getSeller().getUserId() == sellerId)
                .map(Relation::getFollower)
                .collect(Collectors.toList());
    }

    @Override
    public List<Seller> getFollowedSellers(int followerId) {
        return relations.stream()
                .filter(x -> x.getFollower().getUserId() == followerId)
                .map(Relation::getSeller)
                .collect(Collectors.toList());
    }
}
