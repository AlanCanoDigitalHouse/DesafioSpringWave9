package com.api.firstspringchallenge.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static File getFileFromJSON(String path) throws IOException {

        URL url = FileUtils.class
                .getClassLoader()
                .getResource(path);

        if (url == null) {
            throw new FileNotFoundException();
        }

        File file = new File(url.getFile());

        return file;
    }

    public static List getDataFrom(String path, Type type) throws IOException {
        try {
            File file = getFileFromJSON(path);
            String json = new String(Files.readAllBytes(Paths.get(file.toURI())));
            return new Gson().fromJson(json, type);
        } catch (FileNotFoundException exception) {
            return new ArrayList<>();
        }
    }


}
