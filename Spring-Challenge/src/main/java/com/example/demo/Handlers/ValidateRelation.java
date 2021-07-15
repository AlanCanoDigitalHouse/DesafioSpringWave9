package com.example.demo.Handlers;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.ExistingRelationCustomException;
import com.example.demo.Exceptions.InvalidRelationCustomException;
import com.example.demo.Models.UserRelation;

import java.util.List;

public class ValidateRelation {

    public static void ValidateRelation (List<UserRelation> usersRelations, int followerId, int followingId) throws CustomExceptionHandler {
        if(followerId == followingId) { // mismo ID
            throw new InvalidRelationCustomException();
        }
        for(UserRelation r:usersRelations) {
            if(r.getFollower() == followerId && r.getFollowing() == followingId) { // relación existente
                throw new ExistingRelationCustomException();
            }
        }
    }


}
