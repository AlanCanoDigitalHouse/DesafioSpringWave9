package com.mercadolibre.socialmeli.services.imp;

import com.mercadolibre.socialmeli.dto.User;
import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.dto.respons.UserFollowedListResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersResponse;
import com.mercadolibre.socialmeli.dto.respons.UserFollowersListResponse;
import com.mercadolibre.socialmeli.exceptions.ExpectationFailed;
import com.mercadolibre.socialmeli.exceptions.UserBadRequest;
import com.mercadolibre.socialmeli.repositories.UserRepository;
import com.mercadolibre.socialmeli.services.UserServices;
import com.mercadolibre.socialmeli.services.UserToUserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServicesImp implements UserServices {
    UserRepository repo;
    UserToUserService relationService;


    public UserServicesImp(UserRepository repo, UserToUserService relationService) {
        this.repo = repo;
        this.relationService = relationService;
    }

    @Override
    public UserFollowersResponse getCountFollowers(int userid) throws UserBadRequest {
        if (!userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowersResponse response = new UserFollowersResponse();
        User res = repo.findUserById(userid);
        response.setUserId(res.getUserId());
        response.setUserName(res.getUserName());
        response.setFollowers_count(relationService.getAllRelationByUserFollowers(userid).size());
        return response;
    }

    @Override
    public boolean userIsExist(int userid) {
        return Objects.nonNull(repo.findUserById(userid));
    }

    @Override
    public User getUserById(int userid) {
        return repo.findUserById(userid);
    }


    @Override
    public Object postFollow(int userid, int userIdToFollow) throws UserBadRequest, ExpectationFailed {
        if (!userIsExist(userid) || !userIsExist(userIdToFollow)) {
            throw new UserBadRequest();
        }
        relationService.postFollow(userid, userIdToFollow);
        return null;
    }

    @Override
    public Object postUnfollow(int userid, int userIdToFollow) throws UserBadRequest, ExpectationFailed {
        if (!userIsExist(userid) || !userIsExist(userIdToFollow)) {
            throw new UserBadRequest();
        }
        relationService.postUnfollow(userid, userIdToFollow);
        return null;
    }


    @Override
    public UserFollowersListResponse getAllFollowers(int userid, String order) throws UserBadRequest {
        if (!userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowersListResponse result = new UserFollowersListResponse();
        User res = repo.findUserById(userid);
        result.setFollowers(getOrderUser(order, getAllRelationByUserFollowers(userid)));
        result.setUserId(res.getUserId());
        result.setUserName(res.getUserName());
        return result;
    }

    private List<User> getAllRelationByUserFollowers(int userid) {
        List<User> listF = new ArrayList<>();
        for (UserToUser u : relationService.getAllRelationByUserFollowers(userid)) {
            Optional<User> followers = repo.findAllUser().stream().filter(a -> a.getUserId() == u.getUserId()).findFirst();
            listF.add(followers.get());
        }
        return listF;
    }


    @Override
    public UserFollowedListResponse getAllFollowed(int userid, String order) throws UserBadRequest {
        if (!userIsExist(userid)) {
            throw new UserBadRequest();
        }
        UserFollowedListResponse result = new UserFollowedListResponse();
        User res = repo.findUserById(userid);
        result.setUserId(res.getUserId());
        result.setUserName(res.getUserName());
        result.setFollowed(getOrderUser(order, getAllRelationByFollowedByUser(userid)));

        return result;
    }

    private List<User> getAllRelationByFollowedByUser(int userid) {
        List<User> listF = new ArrayList<User>();
        for (UserToUser u : relationService.findAllRelationByUserFollowed(userid)) {
            Optional<User> followers = repo.findAllUser().stream().filter(a -> a.getUserId() == u.getUserIdToFollow()).findFirst();
            listF.add(followers.get());
        }
        return listF;
    }


    @Override
    public List<User> getAllUsers() {
        return repo.findAllUser();
    }

    public List<User> getOrderUser(String order, List<User> users) {
        List<User> or = users;
        if (order != null) {
            switch (order) {
                case "name_asc":
                    or.sort(Comparator.comparing(User::getUserName));
                    break;
                case "name_desc":
                    or.sort(Comparator.comparing(User::getUserName, Comparator.reverseOrder()));
                    break;

            }
        }
        return or;
    }

}
