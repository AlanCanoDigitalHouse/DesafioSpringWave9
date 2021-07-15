package com.mercadolibre.socialmeli.services.imp;

import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.dto.respons.PromProductsResponse;
import com.mercadolibre.socialmeli.dto.respons.PromotionCountResponse;
import com.mercadolibre.socialmeli.dto.respons.PublicationsResponse;
import com.mercadolibre.socialmeli.exceptions.PreconditionError;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.repositories.ProductRepository;
import com.mercadolibre.socialmeli.services.ProductService;
import com.mercadolibre.socialmeli.services.UserServices;
import com.mercadolibre.socialmeli.services.UserToUserService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImp implements ProductService {
    ProductRepository repo;
    UserServices userService;
    UserToUserService relationService;

    public ProductServiceImp(ProductRepository repo, UserServices userService, UserToUserService relationService) {
        this.repo = repo;
        this.userService = userService;
        this.relationService = relationService;
    }

    @Override
    public Publication postPublications(Publication publication) throws UserBadRequest {
        if (!userService.userIsExist(publication.getUserId())) {
            throw new UserBadRequest();
        }
        return repo.addPublication(publication);
    }

    @Override
    public PublicationsResponse getPublicationByFollowed(int userId, String order) throws UserBadRequest {
        if (!userService.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PublicationsResponse response = new PublicationsResponse();
        response.setUserId(userId);
        response.setPosts(getOrderPublication(order, getListBySeller(userId)));
        return response;
    }


    public List<Publication> getListBySeller(int user) {
        List<Publication> result = new ArrayList<>();
        for (UserToUser to : relationService.findAllRelationByUserFollowed(user)) {
            List<Publication> res = repo.findAllByUser(to.getUserIdToFollow());
            result.addAll(res);
        }
        return result.stream().filter(x -> x.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now().minusDays(15)))
                .collect(Collectors.toList());
    }

    @Override
    public Publication postPromotion(Publication promotion) throws UserBadRequest, PreconditionError {
        if (!userService.userIsExist(promotion.getUserId())) {
            throw new UserBadRequest();
        }
        if (promotion.isHasPromo() && promotion.getDiscount() == 0) {
            throw new PreconditionError();
        }
        return repo.addProductPromotion(promotion);
    }

    @Override
    public PromotionCountResponse getCountProm(int userId) throws UserBadRequest {
        if (!userService.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PromotionCountResponse response = new PromotionCountResponse();
        response.setUserId(userId);
        response.setPromoproduct_count(repo.findAllPromoByUser(userId).size());
        response.setUserName(userService.getUserById(userId).getUserName());
        return response;
    }

    @Override
    public PromProductsResponse getProductsByProm(int userId, String order) throws UserBadRequest {
        if (!userService.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PromProductsResponse response = new PromProductsResponse();
        response.setPosts(getOrderPublication(order, repo.findAllPromoByUser(userId)));
        response.setUserId(userId);
        response.setUserName(userService.getUserById(userId).getUserName());
        return response;
    }

    @Override
    public List<Publication> getAll() {
        return repo.findAll();
    }

    public List<Publication> getOrderPublication(String order, List<Publication> pub) {
        List<Publication> or = pub;
        if (order != null) {
            switch (order) {
                case "date_asc":
                    or.sort(Comparator.comparing(Publication::getDate));
                    break;
                case "date_desc":
                    or.sort(Comparator.comparing(Publication::getDate).reversed());
                    break;
                case "name_asc":
                    or.sort(Comparator.comparing(n -> n.getDetail().getProductName()));
                    break;
                case "name_desc":
                    or.sort(Comparator.comparing(n -> n.getDetail().getProductName(), Comparator.reverseOrder()));
                    break;
            }
        }
        return or;
    }

}