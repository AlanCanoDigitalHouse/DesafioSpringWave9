package com.example.demo.Unit.Repositories;

import com.example.demo.Exceptions.CustomExceptionHandler;
import com.example.demo.Exceptions.ExistingNameException;
import com.example.demo.Models.District;
import com.example.demo.Repositories.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

public class DistrictRepositoryTest {

    @Test
    public void shouldAddDistrict() throws CustomExceptionHandler {

        // Arrange
        District district = new District("Broadway", 15.0);
        DistrictRepository districtRepository = new DistrictRepository();
        districtRepository.setDistricts(new ArrayList<>());

        // Act
        districtRepository.addDistrict(district);

        // Assert
        District received = districtRepository.getDistricts().get(0);
        Assertions.assertEquals(district, received);

    }

    @Test
    public void shouldThrowExceptionWhenAddingExistingDistrict() throws CustomExceptionHandler {

        // Arrange
        District district = new District("Broadway", 15.0);
        DistrictRepository districtRepository = new DistrictRepository();
        districtRepository.setDistricts(new ArrayList<>());
        districtRepository.addDistrict(district);

        // Act + Assert
        assertThrows(ExistingNameException.class, () -> districtRepository.addDistrict(district));

    }

    @Test
    public void shouldReturnDistrictNameWhenFindOne() {

        // Arrange
        String districtName =  "Boadway";
        District district = new District(districtName, 22.22);
        DistrictRepository districtRepository = new DistrictRepository();
        List<District> districts =  new ArrayList<>();
        districts.add(district);
        districtRepository.setDistricts(districts);

        // Act
        District received = districtRepository.findDistrictByName(districtName);

        // Assert
        Assertions.assertEquals(district, received);
    }

    @Test
    public void shouldReturnNullWhenDistrictNameNotFound() {

        // Arrange
        String districtName =  "Boadway";
        DistrictRepository districtRepository = new DistrictRepository();

        // Act
        District received = districtRepository.findDistrictByName(districtName);

        // Assert
        Assertions.assertEquals(null, received);
    }

}