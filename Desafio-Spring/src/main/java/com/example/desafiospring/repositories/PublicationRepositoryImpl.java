package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.Publication;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PublicationRepositoryImpl implements IPublicationRepository{

    private final Map<Integer, List<Publication>> publicationsDatabase;
    private final String PATH_NAME_PUBLICATIONS = "src/main/resources/static/publications.json";

    public PublicationRepositoryImpl() {
        this.publicationsDatabase = loadDatabase(this.PATH_NAME_PUBLICATIONS);
    }

    private Map<Integer, List<Publication>> loadDatabase(String pathName) {
        File file = null;
        try {
            file = ResourceUtils.getFile(pathName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file);
    }


    private Map<Integer, List<Publication>> mapObject(File file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TypeReference<Map<Integer, List<Publication>>> typeReference = new TypeReference<>() {
        };
        Map<Integer, List<Publication>> publicationsDtos = null;
        try {
            publicationsDtos= mapper.readValue(file, typeReference);
        } catch (IOException e) {
            System.out.println("No content to map, creating a map empty.");
            publicationsDtos = new HashMap<>();
        }
        return publicationsDtos;
    }

    @Override
    public void updatePublicationFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.writeValue(Paths.get(this.PATH_NAME_PUBLICATIONS).toFile(), this.publicationsDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Publication> getPosts(Integer userId) {
        if(publicationsDatabase.get(userId) == null){
            publicationsDatabase.put(userId, new ArrayList<>());
        }
        return publicationsDatabase.get(userId);
    }
}
