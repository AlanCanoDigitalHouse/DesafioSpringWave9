package com.mercado_libre.bootcamp.spring.desafio.services.seller;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersCountResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowersListResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.dtos.response.SellerInformationResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.Post;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import com.mercado_libre.bootcamp.spring.desafio.repositories.seller.SellerRepositoryImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.relation.RelationServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.strategies.SortUserStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepositoryImpl sellerRepository;

    private final RelationServiceImpl relationService;

    private final SortUserStrategy sortUserStrategy;

    @Override
    public Seller getSeller(int sellerId) {
        return sellerRepository.getSeller(sellerId);
    }

    @Override
    public FollowersCountResponseDTO getFollowersCount(int userId) {

        Seller seller = sellerRepository.getSeller(userId);

        int count = relationService.getFollowers(seller).size();

        return new FollowersCountResponseDTO(seller.getUserId(), seller.getUserName(), count);
    }

    @Override
    public FollowersListResponseDTO getFollowersList(int userId, String order) {

        Seller seller = sellerRepository.getSeller(userId);

        List<User> followers = relationService.getFollowers(seller);

        sortUserStrategy.getImplementation(order).sort(followers);

        return new FollowersListResponseDTO(seller.getUserId(), seller.getUserName(), followers);
    }

    @Override
    public void savePost(int userId, Post post) {
        sellerRepository.savePost(userId, post);
    }

    @Override
    public SellerInformationResponseDTO getSellers() {
        return new SellerInformationResponseDTO(sellerRepository.getSellers());
    }
}
