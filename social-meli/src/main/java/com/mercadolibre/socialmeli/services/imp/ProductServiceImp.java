package com.mercadolibre.socialmeli.services.imp;

import com.mercadolibre.socialmeli.Utils.GenericUtils;
import com.mercadolibre.socialmeli.dto.Publication;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.dto.respons.PromProductsResponse;
import com.mercadolibre.socialmeli.dto.respons.PromotionCountResponse;
import com.mercadolibre.socialmeli.dto.respons.PublicationsResponse;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.repositories.ProductRepository;
import com.mercadolibre.socialmeli.services.ProductService;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImp implements ProductService {
    ProductRepository repo;
    GenericUtils util;

    public ProductServiceImp(ProductRepository repo, GenericUtils util) {
        this.repo = repo;
        this.util = util;
    }

    @Override
    public Publication postPublications(Publication publication) throws UserBadRequest {
        if (!util.userIsExist(publication.getUserId())) {
            throw new UserBadRequest();
        }
        return repo.addPublication(publication);
    }

    @Override
    public PublicationsResponse getPublicationByFollowed(int userId, String order) throws ParseException, UserBadRequest {
        if (!util.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PublicationsResponse response = new PublicationsResponse();
        response.setUserId(userId);
        response.setPosts(util.getOrderPublication(order, getlistBySeller(userId)));
        return response;
    }


    public List<Publication> getlistBySeller(int user) throws ParseException {
        List<Publication> result = new ArrayList<>();
        for (UserToUser to : util.findAllRelationByUserFollowed(user)) {
            List<Publication> res = repo.findAllByUser(to.getUserIdToFollow());
            result.addAll(res);
        }
        return getWeek(result);
    }

    private List<Publication> getWeek(List<Publication> result) throws ParseException {
        List<Publication> response = new ArrayList<>();
        for (Publication d : result) {
            if (util.getDay(d.getDate()) < 15) {
                response.add(d);
            }
        }
        return response;
    }

    @Override
    public Publication postPromotion(Publication promotion) throws UserBadRequest {
        if (!util.userIsExist(promotion.getUserId())) {
            throw new UserBadRequest();
        }
        return repo.addProductPromotion(promotion);
    }

    @Override
    public PromotionCountResponse getCountProm(int userId) throws UserBadRequest {
        if (!util.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PromotionCountResponse response = new PromotionCountResponse();
        response.setUserId(userId);
        response.setPromoproduct_count(repo.findAllPromoByUser(userId).size());
        response.setUserName(util.getUserId(userId).getUserName());
        return response;
    }

    @Override
    public PromProductsResponse getProductsByProm(int userId, String order) throws UserBadRequest {
        if (!util.userIsExist(userId)) {
            throw new UserBadRequest();
        }
        PromProductsResponse response = new PromProductsResponse();
        response.setPosts(util.getOrderPublication(order, repo.findAllPromoByUser(userId)));
        response.setUserId(userId);
        response.setUserName(util.getUserId(userId).getUserName());
        return response;
    }

    @Override
    public List<Publication> getAll() {
        return repo.findAll();
    }

}