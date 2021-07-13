package com.example.desafiospring.repositories;

import com.example.desafiospring.dtos.general.Product;
import com.example.desafiospring.dtos.general.Publication;
import com.example.desafiospring.dtos.general.UserInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        TypeReference<Map<Integer, List<Publication>>> typeReference = new TypeReference<>() {
        };

        Map<Integer, List<Publication>> publicationsDtos = null;
        try {
            publicationsDtos= mapper.readValue(file, typeReference);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Archivo vacío");
            publicationsDtos = new HashMap<>();
        }

        return publicationsDtos;

    }

    @Override
    public void updatePublicationFile() {
        System.out.println("guardando: "+ this.publicationsDatabase);

        try {
            ObjectMapper mapper = new ObjectMapper();

            // convert map to JSON file
            mapper.writeValue(Paths.get(this.PATH_NAME_PUBLICATIONS).toFile(), this.publicationsDatabase);
            System.out.println("Archivo sobreescrito");
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
