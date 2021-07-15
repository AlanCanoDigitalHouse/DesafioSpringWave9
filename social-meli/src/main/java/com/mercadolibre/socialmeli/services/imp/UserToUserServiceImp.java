package com.mercadolibre.socialmeli.services.imp;

import com.mercadolibre.socialmeli.dto.UserToUser;
import com.mercadolibre.socialmeli.exceptions.ExpectationFailed;
import com.mercadolibre.socialmeli.repositories.UserToUserRepository;
import com.mercadolibre.socialmeli.services.UserToUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserToUserServiceImp implements UserToUserService {
    UserToUserRepository repo;

    public UserToUserServiceImp(UserToUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<UserToUser> getAllRelationByUserFollowers(int userid) {
        return repo.findAllRelationByUserFollowers(userid);
    }

    @Override
    public Object postFollow(int userid, int userIdToFollow) throws ExpectationFailed {
        if (repo.relationExist(userid, userIdToFollow)) {
            throw new ExpectationFailed();
        }
        repo.addFollowUser(userid, userIdToFollow);
        return null;
    }

    @Override
    public Object postUnfollow(int userid, int userIdToFollow) throws ExpectationFailed {
        if (!repo.relationExist(userid, userIdToFollow)) {
            throw new ExpectationFailed();
        }
        repo.deleteRelation(userid, userIdToFollow);
        return null;
    }

    @Override
    public List<UserToUser> findAllRelationByUserFollowed(int user) {

        return repo.findAllRelationByUserFollowed(user);
    }

}
