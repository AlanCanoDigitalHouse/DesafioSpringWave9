package com.example.desafiospring.repository.implementations;

import com.example.desafiospring.entities.FollowEntity;
import com.example.desafiospring.exceptions.general.DBNotAvailableException;
import com.example.desafiospring.repository.interfaces.FollowRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class FollowRepositoryImpl implements FollowRepository {

    public static final java.lang.String FOLLOWS_DB_ROUTE = "classpath:static/follows.json";

    @Override
    public void addNewFollow(Integer followerUserId, Integer followedUserId) {
        List<FollowEntity> dbFollows = getDatabaseFollows();
        if (dbFollows.stream().noneMatch(f -> f.getFollowerUserID().equals(followerUserId) && f.getFollowedUserID().equals(followedUserId))) {
            dbFollows.add(new FollowEntity(followerUserId, followedUserId));
            overWriteFollowsDB(dbFollows);
        } else {
            log.info("Follow already existed");
        }
    }

    @Override
    public List<Integer> getFollowerIDs(Integer userId) {
        return getDatabaseFollows().stream().filter(f -> f.getFollowedUserID().equals(userId)).map(FollowEntity::getFollowerUserID).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getFollowedIDs(Integer userId) {
        return getDatabaseFollows().stream().filter(f -> f.getFollowerUserID().equals(userId)).map(FollowEntity::getFollowedUserID).collect(Collectors.toList());
    }

    @Override
    public void deleteFollow(Integer followerUserId, Integer followedUserId) {
        List<FollowEntity> dbFollows = getDatabaseFollows();
        if (dbFollows.removeIf(fe -> fe.getFollowerUserID().equals(followerUserId)&&fe.getFollowedUserID().equals(followedUserId))){
            overWriteFollowsDB(dbFollows);
        }else{
            log.info("Follow didn't exist");
        }
    }

    private void overWriteFollowsDB(List<FollowEntity> followsToWrite) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(ResourceUtils.getFile(FOLLOWS_DB_ROUTE), followsToWrite);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error writing to DB", e);
        }
    }

    private List<FollowEntity> getDatabaseFollows() {
        File file;
        try {
            file = ResourceUtils.getFile(FOLLOWS_DB_ROUTE);
        } catch (Exception e) {
            throw new DBNotAvailableException("Error finding DB", e);
        }
        return loadUsersJSON(file);
    }

    private List<FollowEntity> loadUsersJSON(File file) throws DBNotAvailableException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FollowEntity>> typeReference = new TypeReference<>() {
        };
        List<FollowEntity> users;
        try {
            users = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new DBNotAvailableException("Error reading DB", e);
        }
        return users;
    }
}
