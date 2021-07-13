package com.socialMeli.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialMeli.SocialMeliApplication;
import com.socialMeli.exception.exception.ModelAlreadyExists;
import com.socialMeli.exception.exception.ModelNotExists;
import com.socialMeli.model.AbstractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends AbstractModel> {
    final Logger logger = LoggerFactory.getLogger(SocialMeliApplication.class);

    /* ABSTRACTIONS */
    protected abstract void saveDatabase(List<T> abstractModels);

    protected abstract List<T> loadDatabase();

    protected abstract List<T> mapObject(File file);



    /* IMPLEMENTATIONS */

    public void insert(T model) throws ModelAlreadyExists {
        List<T> models = this.loadDatabase();
        if (modelAlreadyExists(models, model)) throw new ModelAlreadyExists(String.valueOf((model).getId()));
        models.add(model);
        this.saveDatabase(models);
    }

    public void modify(T model) throws ModelNotExists {
        List<T> models = this.loadDatabase();
        if (!modelAlreadyExists(models, model)) throw new ModelNotExists(model.getModelClassName());
        //Get actual model to replace
        T actualModel = models.stream().filter(x -> x.getId() == model.getId()).findAny().get();
        int location = models.indexOf(actualModel);

        //Replace
        models.set(location, model);
        saveDatabase(models);
    }

    public List<T> findAll() {
        return loadDatabase();
    }

    public void delete(T model) throws ModelNotExists {
        List<T> models = loadDatabase();
        T finded = findModelInAList(models, model);
        models.remove(finded);
        saveDatabase(models);
    }

    public T findById(int modelID) throws ModelNotExists {
        List<T> abstractModels = this.loadDatabase();
        Optional<T> possibleModel = abstractModels.stream().filter(x -> x.getId() == modelID).findAny();
        if (possibleModel.isEmpty()) throw new ModelNotExists(String.valueOf(modelID));
        return possibleModel.get();
    }




    /* UTILS */


    protected T findModelInAList(List<T> models, T model) throws ModelNotExists {
        Optional<T> possibleModel = models.stream().filter(x -> x.getId() == model.getId()).findAny();
        if (possibleModel.isEmpty()) throw new ModelNotExists(model.getModelClassName());
        return possibleModel.get();
    }

    protected boolean modelAlreadyExists(List<T> models, T model) {
        Optional<T> possibleModel = models.stream().filter(x -> x.getId() == model.getId()).findAny();
        if (possibleModel.isEmpty())
            return false;
        return true;
    }

    protected void saveDatabase(List<T> list, String fileName) {
        try {
            //To determine de name of the file to write is defined by the model used
            File file = new File(getPathFile(fileName));
            if (file.createNewFile()) logger.info("Created file db!");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
            logger.info("Models wrote to db!");
        } catch (Exception e) {
            logger.warn("Error occurred when writing to db");
            e.printStackTrace();
        }
    }

    protected List<T> loadDatabase(String fileName) {
        File file = null;
        try {
            file = new File(getPathFile(fileName));
            if (file.createNewFile()) {
                logger.info("File created: " + file.getName());
            }
        } catch (IOException e) {
            logger.warn("An error occurred");
            e.printStackTrace();
        }

        return mapObject(file);
    }

    private String getPathFile(String name) {
        return "./" + name + ".json";
    }

}
