package com.socialMeli.service;

import com.socialMeli.exception.exception.AlreadyFollowedException;
import com.socialMeli.exception.exception.FollowHimselfException;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.UserModel;
import com.socialMeli.repository.UserRepository;
import com.socialMeli.utils.AppStartupRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    UserService userService = new UserService(new UserRepository());
    @Test
    public void followAUser(){
        AppStartupRunner.refresh();
        try{
            userService.follow(1,2);
            Assertions.assertTrue(true);
        }catch (FollowHimselfException | AlreadyFollowedException | ModelNotExists e){
            Assertions.fail();
        }
    }
}
