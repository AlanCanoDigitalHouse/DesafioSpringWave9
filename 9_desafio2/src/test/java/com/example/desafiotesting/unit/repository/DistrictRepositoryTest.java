package com.example.desafiotesting.unit.repository;

import com.example.desafiotesting.exceptions.DistrictException;
import com.example.desafiotesting.exceptions.FileException;
import com.example.desafiotesting.models.District;
import com.example.desafiotesting.repository.DistrictRepository;
import com.example.desafiotesting.unit.TestUtilsGenerator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistrictRepositoryTest {

    DistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        TestUtilsGenerator.fillDistrictsFile();
        this.districtRepository = new DistrictRepository();
    }

    @Test
    @DisplayName("Find District by Name Success")
    public void testFindDistrictByName() throws Exception {
        //Arrange
        District expected = new District(2, "Belgrano", 1100.0);
        //Act
        District current = districtRepository.findByName("Belgrano");
        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("District by Name Not Found")
    public void testFindDistrictByNameNotFound() throws Exception {
        // assert
        Assertions.assertThrows(DistrictException.class, () -> districtRepository.findByName("Bronx"));
    }

    @Test
    @DisplayName("Find District by Id Success")
    public void testFindDistrictById() throws Exception {
        //Arrange
        District expected = new District(1, "Palermo", 1000.0);
        //Act
        District current = districtRepository.findById(1);
        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("District by Id Not Found")
    public void testFindDistrictByIdNotFound() throws DistrictException {
        // assert
        Assertions.assertThrows(DistrictException.class, () -> districtRepository.findById(99));
    }

    @Test
    @DisplayName("Can't Read file Exception")
    public void testCantReadFile() throws Exception {
        //Arrange
        //Call to static method that fills the file with a not valid json random string
        TestUtilsGenerator.corruptDistrictsFile();
        // assert
        Assertions.assertThrows(FileException.class, () -> districtRepository.findById(1));
    }
}
