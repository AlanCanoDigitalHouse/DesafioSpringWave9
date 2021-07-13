package com.socialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialMeli.SocialMeliApplication;
import com.socialMeli.exception.exception.EmptyModelList;
import com.socialMeli.model.UserModel;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<UserModel> {
    final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);
    final String FILE_NAME = "user";

    /**
     * Save a list of models, the file will be cleaned and re writed
     *
     * @param abstractModels
     */
    protected void saveDatabase(List<UserModel> abstractModels) {
        super.saveDatabase(abstractModels, FILE_NAME);
    }

    /**
     * Get all models of the file
     *
     * @return list of the models
     */
    protected List<UserModel> loadDatabase() {
        return super.loadDatabase(FILE_NAME);
    }

    @Override
    protected List<UserModel> mapObject(File file ) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserModel>> typeReference = new TypeReference<>() {
        };
        List<UserModel> actorModels;
        try {
            actorModels = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            logger.error("Cant read json (probably is empty) full error: " + e.getMessage());
            actorModels = new ArrayList<>();
        }
        return actorModels;
    }


}
