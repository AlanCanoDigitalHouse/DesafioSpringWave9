package com.socialMeli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialMeli.model.AbstractModel;
import com.socialMeli.model.PostModel;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository extends AbstractRepository<PostModel> {
    final String FILE_NAME = "post";

    /**
     * Save a list of models, the file will be cleaned and re wrote
     * <p>
     * All the logic is in the super class, here only is specified the name of file
     */
    @Override
    protected void saveDatabase(List<PostModel> abstractModels) {
        super.saveDatabase(abstractModels, FILE_NAME);
    }

    /**
     * Get all models of the file
     * <p>
     * All the logic is in the super class, here only is specified the name of file
     */
    @Override
    protected List<PostModel> loadDatabase() {
        return super.loadDatabase(FILE_NAME);
    }

    @Override
    protected List<PostModel> mapObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostModel>> typeReference = new TypeReference<>() {
        };
        List<PostModel> models;
        try {
            models = objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            logger.error("Cant read json (probably is empty) full error: " + e.getMessage());
            models = new ArrayList<>();
        }
        return models;
    }

    public int getNextId() {
        List<PostModel> models = findAll();
        Optional<Integer> optionMax = models.stream().map(AbstractModel::getId).max(Comparator.naturalOrder());
        if (optionMax.isEmpty()) return 1;
        return optionMax.get() + 1;
    }
}
