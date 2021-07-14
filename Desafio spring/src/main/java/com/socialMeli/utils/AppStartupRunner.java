package com.socialMeli.utils;

import com.socialMeli.SocialMeliApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * To make the development more easy, at the start of spring will be copied the files dataSet*.json
 * to the database files, with this we have the same data in every start of spring
 */
@Component
public class AppStartupRunner implements ApplicationRunner {
    final static Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);

    @Value("${resetDataAtStart}")
    private boolean refresh;
    @Override
    public void run(ApplicationArguments args) {
        if(refresh){
            logger.warn("Borrando y colocando datos por defecto");
            refresh();
        }
    }

    public static void refresh() {
        copyToFile("./dataSetPost.json", "./post.json");
        copyToFile("./dataSetUser.json", "./user.json");
    }

    public static void copyToFile(String from, String to) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            createIfNotExists(to);
            fr = new FileReader(from);
            fw = new FileWriter(to);
            int c = fr.read();
            while (c != -1) {
                fw.write(c);
                c = fr.read();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            logger.info("Finished creation of default data " + to);
        }
    }

    public static void createIfNotExists(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) logger.info("Created new file " + path);
        } catch (IOException e) {
            logger.error("Cant generate default data");
        }
    }
}
