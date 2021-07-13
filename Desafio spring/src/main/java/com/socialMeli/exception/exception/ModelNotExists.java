package com.socialMeli.exception.exception;

import com.socialMeli.SocialMeliApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelNotExists extends Exception {
    final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);

    public ModelNotExists(String modelName) {
        super("The model " + modelName + " not exists");
        logger.error("The model " + modelName + " not exists");
    }
}
