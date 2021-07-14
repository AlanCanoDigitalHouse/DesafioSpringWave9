package com.example.desafiospring.repositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Collection;
import java.util.List;

public interface IRepository<T, ID, E extends Exception> {
    T findById(ID id) throws E;

    Collection<T> findByIds(Collection<ID> ids);

    default List<T> mapObject(File file, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, tClass);
        List<T> items = null;
        try {
            items = objectMapper.readValue(file, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    default List<T> loadDB(String filename, Class<T> tClass) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/" + filename);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapObject(file, tClass);
    }

    default void saveToFile(String filename, List<T> items) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/" + filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String listJson = objectMapper.writeValueAsString(items);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listJson);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
