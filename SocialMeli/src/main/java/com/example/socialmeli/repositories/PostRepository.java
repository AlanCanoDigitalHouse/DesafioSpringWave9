package com.example.socialmeli.repositories;

import com.example.socialmeli.dtos.PostDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository{

    private List<PostDto> postDtos;

    public PostRepository(List<PostDto> postDtos) {
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:static/posts.json");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        this.postDtos = mapObject(file);
    }

    @Override
    public List<PostDto> getPosts() {
        return this.postDtos;
    }

    private List<PostDto> mapObject(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostDto>> typeReference = new TypeReference<>(){};
        List<PostDto> postDtos = null;
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            postDtos = objectMapper.readValue(file, typeReference);
        }catch (IOException e){
            e.printStackTrace();
        }
        return postDtos;
    }

    @Override
    public void saveFile() {
        try {
            var file = ResourceUtils.getFile("classpath:static/posts.json");
            var mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.writeValue(file, this.postDtos);
        } catch (IOException exception) {
            exception.printStackTrace();
            //throw new DatabaseException("There was an error while writing on the database");
        }
    }
}
