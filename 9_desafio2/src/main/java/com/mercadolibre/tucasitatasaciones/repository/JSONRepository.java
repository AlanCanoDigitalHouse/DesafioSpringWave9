package com.mercadolibre.tucasitatasaciones.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.tucasitatasaciones.exception.DatabaseException;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONRepository<T> {
    private final String DATA_DIR;
    private final Class<T> elementClass;
    protected List<T> data;

    public JSONRepository(String DATA_DIR, Class<T> elementClass) {
        this.DATA_DIR = DATA_DIR;
        this.elementClass = elementClass;
    }

    protected List<T> loadDatabase() {
        try {
            var file = ResourceUtils.getFile(DATA_DIR);
            return mapFromDatabase(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            throw new DatabaseException("There was an error while reading from the database");
        }
    }

    private List<T> mapFromDatabase(File file) {
        var objectMapper = new ObjectMapper();
        var listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);

        try {
            return objectMapper.readValue(file, listType);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new DatabaseException("There was an error while reading from the database");
        }
    }

    protected void writeDatabase(List<T> data) {
        try {
            var file = ResourceUtils.getFile(DATA_DIR);
            var mapper = new ObjectMapper();
            mapper.writeValue(file, new ArrayList<>(data));
            setData();
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new DatabaseException("There was an error while writing on the database");
        }
    }

    public List<T> getData() {
        if (Objects.isNull(data)) {
            setData();
        }
        return data;
    }

    public void setData() {
        this.data = loadDatabase();
    }
}
