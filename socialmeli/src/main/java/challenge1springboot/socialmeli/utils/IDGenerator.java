package challenge1springboot.socialmeli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

    private final String generatorPath;
    private final AtomicInteger autoIncrement;

    public IDGenerator(String path) {
        this.generatorPath = path;
        this.autoIncrement = new AtomicInteger(initID());
    }

    public int next() {
        int id = autoIncrement.getAndIncrement();
        saveLast(id);
        return id;
    }

    private int initID() {
        try {
            int id = new ObjectMapper().readValue(JSONReader.readJSONFile(generatorPath),Integer.class) + 1;
            saveLast(id);
            return id;
        } catch (IOException e) {
            return 0;
        }
    }

    private void saveLast(int id) {
        try {
            new ObjectMapper().writeValue(JSONReader.readJSONFile(generatorPath), id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
