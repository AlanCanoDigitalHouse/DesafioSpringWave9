package com.socialMeli.repository;

import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    public void createModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userRepository.insert(userModel);

        Assertions.assertEquals(1,userRepository.findById(1).getId());
    }

    @Test
    public void modifyModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userRepository.insert(userModel);
        userModel.setUserName("DISTINTO");
        userRepository.modify(userModel);
        Assertions.assertEquals("DISTINTO", userRepository.findById(1).getUserName());
    }

    @Test
    public void deleteModel() throws ModelAlreadyExists, ModelNotExists {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userRepository.insert(userModel);
        userRepository.delete(userModel);

        try{
            UserModel finded = userRepository.findById(1);
            Assertions.assertTrue(false);
        }catch (ModelNotExists ex){
            Assertions.assertTrue(true);
        }
    }

    @BeforeEach
    public void deleteFileTest(){
        final String FILE_NAME = "./user.json";
        File file = null;
        file = new File(FILE_NAME);
        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        }
    }
}
