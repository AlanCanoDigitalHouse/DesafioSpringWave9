package com.desafio.spring.repositories;

import com.desafio.spring.dtos.ClientDto;
import com.desafio.spring.dtos.SellerDto;
import com.desafio.spring.dtos.UserDto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private static Map<Integer, UserDto> users = mappingUsers();

    private static List<List<Integer>> follows = matrixUsers();

    private static List<List<Integer>> matrixUsers() {
        return new ArrayList<>();
    }

    private static Map<Integer, UserDto> mappingUsers() {
        Map<Integer, UserDto> users = new HashMap<>();
        users.put(1235, new ClientDto(1235, "Cliente1"));
        users.put(1569, new SellerDto(1569, "Vendedor2"));
        return users;
    }

    @Override
    public void addNew(Integer userId, Integer userIdToFollow) {
        List<Integer> users_ids = new ArrayList<>();
        users_ids.add(userId);
        users_ids.add(userIdToFollow);
        follows.add(users_ids);
    }

    @Override
    public void deleteFollower(Integer userId, Integer userIdToUnfollow) {
        List<Integer> users_ids = new ArrayList<>();
        users_ids.add(userId);
        users_ids.add(userIdToUnfollow);
        follows.removeIf(e -> e.equals(users_ids));
    }

    @Override
    public UserDto findUser(Integer userId) {
        return users.get(userId);
    }

    @Override
    public List<UserDto> allFollowers(Integer userId, String orderBy) {
        List<UserDto> getData = follows.stream().filter(u -> u.get(1).equals(userId)).map(e -> findUser(e.get(0))).collect(Collectors.toList());
        switch (orderBy)
        {
            case "name_asc":  getData.sort(Comparator.comparing(UserDto::getUserName));
                break;
            case "name_desc":  getData.sort(Comparator.comparing(UserDto::getUserName).reversed());
                break;
            default: return getData;
        }
        return getData;
    }

    @Override
    public List<UserDto> allFollowed(Integer userId, String order) {
        List<UserDto> result = follows.stream().filter(u -> u.get(0).equals(userId)).map(e -> findUser(e.get(1))).collect(Collectors.toList());
        switch (order)
        {
            case "name_asc":  result.sort(Comparator.comparing(UserDto::getUserName));
                break;
            case "name_desc":  result.sort(Comparator.comparing(UserDto::getUserName).reversed());
                break;
            default: return result;
        }
        return result;
    }

    @Override
    public Collection<Object> allFollowers(Integer userId) {
        return follows.stream().filter(u -> u.get(1).equals(userId)).map(e -> findUser(e.get(0))).collect(Collectors.toList());
    }
}
