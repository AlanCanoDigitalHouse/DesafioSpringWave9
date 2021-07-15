package com.socialMeli.repository;

import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class UserRepositoryTest {
    final UserRepository userRepository = new UserRepository();

    @Test
    public void createModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = UserModel.builder().id(1).build();
        userRepository.insert(userModel);

        Assertions.assertEquals(1,userRepository.findById(1).getId());
    }

    @Test
    public void modifyModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = UserModel.builder().id(1).build();

        userRepository.insert(userModel);
        userModel.setUserName("DIFFERENT");
        userRepository.modify(userModel);
        Assertions.assertEquals("DIFFERENT", userRepository.findById(1).getUserName());
    }

    @Test
    public void deleteModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = UserModel.builder().id(1).build();

        userRepository.insert(userModel);
        userRepository.delete(userModel);

        try{
            userRepository.findById(1);
            Assertions.fail();
        }catch (ModelNotExists ex){
            Assertions.assertTrue(true);
        }
    }

    @BeforeEach
    public void deleteFileTest(){
        final String FILE_NAME = "./user.json";
        File file;
        file = new File(FILE_NAME);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        }
    }
}
