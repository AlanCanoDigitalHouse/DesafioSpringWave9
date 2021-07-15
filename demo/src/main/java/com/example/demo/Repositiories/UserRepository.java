package com.example.demo.Repositiories;

import com.example.demo.Entities.User;
import lombok.Data;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class UserRepository implements iUserRepository{

    public   HashMap<Integer, User> UserRepositoriesMap = new HashMap<>();
    private static UserRepository instance= null;


    private UserRepository(){ }

    public static UserRepository getInstance(){
        if (instance==null){
            instance= new UserRepository();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void initialize(){
        User u1 = new User(1,"pepe");
        User u2 = new User(2,"julian");
        User u3 = new User(3,"carlos");
        User u4 = new User(4,"miguel");
        User u5 = new User(5,"lucas");
        User u6 = new User(6,"rama");
        User u7 = new User(7,"tomas");
        User u8 = new User(8,"toto");
        User u9 = new User(9,"lgante");
        User u10 = new User(10,"tony");

        saveUser(u1);
        saveUser(u2);
        saveUser(u3);
        saveUser(u4);
        saveUser(u5);
        saveUser(u6);
        saveUser(u7);
        saveUser(u8);
        saveUser(u9);
        saveUser(u10);
    };

    @Override
    public User findById(Integer a) {
        return this.UserRepositoriesMap.get(a);
    }

    @Override
    public User saveUser(User a) {
        this.UserRepositoriesMap.put(a.getUserid(),a);
        return a;
    }

}
