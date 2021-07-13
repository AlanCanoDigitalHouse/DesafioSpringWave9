package com.mercadolibre.socialmeli.services.imp;

import com.mercadolibre.socialmeli.Utils.GenericUtils;
import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.respons.UserFollowedListResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersListResponse;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.repositories.UserRepository;
import com.mercadolibre.socialmeli.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserServices {
    UserRepository repo;
    GenericUtils util;

    public UserServicesImp(UserRepository repo, GenericUtils util) {
        this.repo = repo;
        this.util = util;
    }

    @Override
    public Object postFollow(int userid, int userIdToFollow) throws UserBadRequest {
        if (!util.userIsExist(userid) || !util.userIsExist(userIdToFollow)) {
            throw new UserBadRequest();
        } else {
            repo.addFollowUser(userid, userIdToFollow);
        }
        return null;
    }

    @Override
    public UserFollowersResponse getCountFollowers(int userid) throws UserBadRequest {
        if (!util.userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowersResponse response = new UserFollowersResponse();
        User res = util.getUserId(userid);
        response.setUserId(res.getUserId());
        response.setUserName(res.getUserName());
        response.setFollowers_count(getFollowersCount(userid));
        return response;
    }

    private int getFollowersCount(int userid) {
        int count = repo.findAllRelationByUserFollowers(userid).size();
        return count;
    }


    @Override
    public UserFollowersListResponse getAllFollowers(int userid, String order) throws UserBadRequest {
        if (!util.userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowersListResponse result = new UserFollowersListResponse();
        User res = util.getUserId(userid);
        result.setFollowers(util.getOrderUser(order, repo.findFollowersByUser(userid)));
        result.setUserId(res.getUserId());
        result.setUserName(res.getUserName());
        return result;
    }


    @Override
    public UserFollowedListResponse getAllFollowed(int userid, String order) throws UserBadRequest {
        if (!util.userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowedListResponse result = new UserFollowedListResponse();
        User res = util.getUserId(userid);
        result.setUserId(res.getUserId());
        result.setUserName(res.getUserName());
        result.setFollowed(util.getOrderUser(order, repo.findFollowedByUser(userid)));
        return result;
    }

    @Override
    public Object postUnfollow(int userid, int userIdToFollow) throws UserBadRequest {
        if (!util.userIsExist(userid) || !util.userIsExist(userIdToFollow)) {
            throw new UserBadRequest();
        }
        repo.deleteRelation(userid, userIdToFollow);
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAllUser();
    }

}
