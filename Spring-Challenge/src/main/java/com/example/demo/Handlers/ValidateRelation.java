package com.example.demo.Handlers;

import com.example.demo.Exceptions.ExceptionHandler;
import com.example.demo.Exceptions.ExistingRelationException;
import com.example.demo.Exceptions.InvalidRelationException;
import com.example.demo.Models.UserRelation;
import com.example.demo.Repositories.IUserRepository;
import com.example.demo.Repositories.UserRepository;

import java.util.List;

public class ValidateRelation {

    public static void ValidateRelation (List<UserRelation> usersRelations, int followerId, int followingId) throws ExceptionHandler {
        if(followerId == followingId) { // mismo ID
            throw new InvalidRelationException();
        }
        for(UserRelation r:usersRelations) {
            if(r.getFollower() == followerId && r.getFollowing() == followingId) { // relación existente
                throw new ExistingRelationException();
            }
        }
    }


}
