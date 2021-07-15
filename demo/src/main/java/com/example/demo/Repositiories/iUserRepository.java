package com.example.demo.Repositiories;

import com.example.demo.Entities.User;

public interface iUserRepository {
    void initialize();
    User findById(Integer a);
    User saveUser(User a);


}
