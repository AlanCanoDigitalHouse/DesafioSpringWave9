package com.example.desafiospring.repositories.implementation;

import com.example.desafiospring.entities.User;
import com.example.desafiospring.enums.ConstantEnum;
import com.example.desafiospring.repositories.IUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private final ObjectMapper mapper;

    public UserRepository() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return this.loadDataBase();
    }

    @Override
    public User findByIdAndType(Long userId, boolean isSeller) throws IOException {
        Optional<User> user = this.loadDataBase().stream()
                .filter(x -> x.getUserId().equals(userId) && x.getIsSeller() == isSeller).findFirst();
        if (user.isEmpty())
            return null;
        return user.get();
    }

    @Override
    public User findById(Long userId) throws IOException {
        Optional<User> user = this.loadDataBase().stream()
                .filter(x -> x.getUserId().equals(userId)).findFirst();
        if (user.isEmpty())
            return null;
        return user.get();
    }

    private void writeDataBase(List<User> users) throws IOException {
        mapper.writeValue(new File(ConstantEnum.JSON_USERS), users);
    }

    private List<User> loadDataBase() throws IOException {
        return mapObject(Paths.get(ConstantEnum.JSON_USERS).toFile());
    }

    private List<User> mapObject(File file) throws IOException {
        return mapper.readValue(file, new TypeReference<>(){});
    }

}
