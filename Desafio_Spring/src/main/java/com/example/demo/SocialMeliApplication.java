package com.example.demo;

import com.example.demo.cache.CacheConfiguration;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;

@SpringBootApplication
@EnableCaching
public class SocialMeliApplication implements CommandLineRunner {

    @Autowired
    private CacheConfiguration cacheConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(SocialMeliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User testUser = new User(1, "Brian", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(1, testUser);
        User testUser2 = new User(2, "Alonso", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(2, testUser2);
        User testUser3 = new User(3, "Miriam", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(3, testUser3);
        User testUser4 = new User(4, "Pedro", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(4, testUser4);
        User testUser5 = new User(5, "Emiliano", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(5, testUser5);
        User testUser6 = new User(6, "Edwin", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        cacheConfiguration.userCache.put(6, testUser6);
    }
}
