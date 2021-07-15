package com.mercado_libre.bootcamp.spring.desafio.services.follow;

import com.mercado_libre.bootcamp.spring.desafio.dtos.response.FollowResponseDTO;
import com.mercado_libre.bootcamp.spring.desafio.models.Seller;
import com.mercado_libre.bootcamp.spring.desafio.models.User;
import com.mercado_libre.bootcamp.spring.desafio.services.relation.RelationServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.seller.SellerServiceImpl;
import com.mercado_libre.bootcamp.spring.desafio.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final UserServiceImpl userService;

    private final SellerServiceImpl sellerService;

    private final RelationServiceImpl relationService;

    @Override
    public HttpStatus followSeller(int userId, int userIdToFollow) {

        checkIfTheyAreTheSameUser(userId, userIdToFollow);

        User user = userService.getUser(userId);
        Seller seller = sellerService.getSeller(userIdToFollow);

        relationService.addFollower(seller, user);

        return HttpStatus.OK;
    }

    @Override
    public FollowResponseDTO unfollowSeller(int userId, int userIdToFollow) {

        checkIfTheyAreTheSameUser(userId, userIdToFollow);

        User user = userService.getUser(userId);
        Seller seller = sellerService.getSeller(userIdToFollow);

        relationService.unfollowSeller(seller, user);

        return new FollowResponseDTO("OK");
    }

    private void checkIfTheyAreTheSameUser(int userId, int userIdToFollow) {
        if (userId == userIdToFollow) {
            throw new RuntimeException();
        }
    }
}
