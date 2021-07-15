package com.example.demo.cache;

import com.example.demo.dtos.PostDTO;
import com.example.demo.models.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

@Component
public class CacheConfiguration {
    public Cache<Integer, User> userCache = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(1000)
            .build();

    public Cache<Integer, PostDTO> postCache = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(1000)
            .build();
}
