package com.meli.desafiospring.repositories;

import com.meli.desafiospring.DTOs.response.PostResponseDTO;
import com.meli.desafiospring.DTOs.response.DetailResponseDTO;
import com.meli.desafiospring.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository implements IUserRepository{

    List<User> users;

    public UserRepository() { this.users = new ArrayList<User>(); }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> findById(Integer userId) {
        return users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
    }

    public List<User> initialize() {
        List<PostResponseDTO> postList1 = new ArrayList<>();
        PostResponseDTO post1 = new PostResponseDTO(13L, "13-07-2021", new DetailResponseDTO(),
                                        "moto", 700.0);
        PostResponseDTO post2 = new PostResponseDTO(16L, "09-07-2021", new DetailResponseDTO(),
                "moto2", 574.0);
        postList1.add(post1);
        postList1.add(post2);

        List<PostResponseDTO> postList2 = new ArrayList<>();
        PostResponseDTO post3 = new PostResponseDTO(32L, "05-07-2021", new DetailResponseDTO(),
                "auto", 57.3);
        postList2.add(post3);

        List<User> userList1 = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();
        User user1 = new User(1235, "Juan", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<User> userList3 = new ArrayList<>();
        List<User> userList4 = new ArrayList<>();
        User user2 = new User(2500, "Romina", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<User> userList5 = new ArrayList<>();
        List<User> userList6 = new ArrayList<>();
        User user3 = new User(1222, "Norma", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        user1.follow(user3);
        user2.follow(user3);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

}
