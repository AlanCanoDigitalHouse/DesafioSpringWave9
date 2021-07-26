package testingchallengue.demo.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import testingchallengue.demo.exceptions.ExistentDistrictNameException;
import testingchallengue.demo.models.District;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
@Data
@AllArgsConstructor

public class DistrictRepository implements IDistrictRepository {

    private List<District> districts;

    public DistrictRepository() {
        this.districts = loadDistrictsDB();
    }

    //metodo para encontrar distrito por nombre
    @Override
    public District findDistrictByName(String name) {
        return districts.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }

    //agrego nuevo distrito
    @Override
    public void addDistrict(District district) throws ExistentDistrictNameException {
        District existent = findDistrictByName(district.getName());

        if (existent == null) {
            districts.add(district);
        } else {
            throw new ExistentDistrictNameException(district.getName());
        }
    }

    //inicializo la base de datos
    private List<District> loadDistrictsDB() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:districts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<District>> typeRef = new TypeReference<>() {
        };

        List<District> db = null;

        try {
            db = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }
}
