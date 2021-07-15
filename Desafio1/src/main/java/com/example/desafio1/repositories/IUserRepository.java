package com.example.desafio1.repositories;

import com.example.desafio1.dto.User;
import com.example.desafio1.exceptions.annotations.UserFollows;
import com.example.desafio1.exceptions.annotations.UserId;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintTarget;
import javax.validation.Valid;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import java.util.List;

@Validated
public interface IUserRepository {
    @UserFollows
    void addNewFollower(@UserId Integer userId,@UserId Integer userIdToFollow);

    void unfollow(@UserId Integer userId,@UserId Integer userIdToUnfollow);
    User findUserById(Integer userId);
    List<User> listFollowers(@UserId Integer userId);
    List<User> listFollowed(@UserId Integer userId);
    boolean isFollowing(Integer followingUserId, Integer followedUserId);
}
