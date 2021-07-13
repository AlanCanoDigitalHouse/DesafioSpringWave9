package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.response.UserResponseDto;
import com.example.desafiospring.exceptions.ElementDoesntFindException;
import com.example.desafiospring.exceptions.LogicValidationException;
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
    public void delete(Integer id) {
        /**TODO
         * delete if needed
         */
    }

    public List<UserResponseDto> getAll() {
        return this.users;
    }

    @Override
    public UserResponseDto create(UserResponseDto element) {
        users.add(element);
        return element;
    }

    @Override
    public void update(UserResponseDto newElement) {

        var index = users.indexOf(newElement);
        if (index != -1) {
            users.set(index, newElement);
        } else {
            throw new LogicValidationException("User not found to update");
        }

    }

    public void setGeneratedData(List<UserResponseDto> users) {
        this.users = users;
    }
}
