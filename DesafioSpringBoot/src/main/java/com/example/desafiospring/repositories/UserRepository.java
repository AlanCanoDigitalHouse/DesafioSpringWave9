package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.ElementDoesntFindException;
import com.example.desafiospring.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements CRUD<UserResponseDto> {
    private List<UserResponseDto> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public UserResponseDto getById(Integer id) {
        var user = users.stream()
                .filter(userResponseDto -> userResponseDto.getId() == id)
                .findFirst();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("The user is not found in the collection");
        }
    }

    @Override
    public List<UserResponseDto> getAll() {
        return this.users;
    }

    @Override
    public UserResponseDto create(UserResponseDto element) {
        users.add(element);
        return element;
    }

    @Override
    public Boolean update(UserResponseDto newElement) {
        try {
            var index = users.indexOf(newElement);
            var prevElement = users.set(index, newElement);
            return prevElement.getId() == newElement.getId();
        } catch (NullPointerException ex) {
            throw new ElementDoesntFindException("User not found to update");
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(UserResponseDto element) {
        return false;
    }

    public void setGeneratedData(List<UserResponseDto> users){
        this.users = users;
    }
}
