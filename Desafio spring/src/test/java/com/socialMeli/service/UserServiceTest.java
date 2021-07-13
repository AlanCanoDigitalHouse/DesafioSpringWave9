package com.socialMeli.service;

import com.socialMeli.exception.exception.AlreadyFollowedException;
import com.socialMeli.exception.exception.FollowHimselfException;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.repository.UserRepository;
import com.socialMeli.utils.AppStartupRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    final UserService userService = new UserService(new UserRepository());
    @Test
    public void followAUser(){

        try{
            userService.follow(1,2);
            Assertions.assertTrue(true);
        }catch (FollowHimselfException | AlreadyFollowedException | ModelNotExists e){
            Assertions.fail();
        }
    }

    @Test
    public void following_counter(){
        try{
            int counter = userService.getCountOfFollowers(4).getFollowers_count();
            Assertions.assertEquals(3,counter);
        }catch (ModelNotExists e){
            Assertions.fail();
        }
    }

    @BeforeEach
    public void refresh(){
        AppStartupRunner.refresh();
    }
}
