package com.mercadolibre.tucasitatasaciones.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONDataUtil {

    public static void cleanData(String dataDir) {
        try {
            var file = ResourceUtils.getFile(dataDir);
            var mapper = new ObjectMapper();
            mapper.writeValue(file, new ArrayList<>());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static <T> void addTestData(String dataDir, List<T> data) {
        try {
            var file = ResourceUtils.getFile(dataDir);
            var mapper = new ObjectMapper();
            mapper.writeValue(file, new ArrayList<>(data));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
