package com.mercadolibre.calculadorametroscuadrados.units.repository;

import com.mercadolibre.calculadorametroscuadrados.exceptions.environmentValidationException.exceptions.DataBaseNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.models.EnvironmentModel;
import com.mercadolibre.calculadorametroscuadrados.repository.IEnvironmentRepository;
import com.mercadolibre.calculadorametroscuadrados.repository.EnvironmentRepository;
import com.mercadolibre.calculadorametroscuadrados.units.util.UtilTest;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit Test - EnvironmentRepository")
public class EnvironmentRepositoryUnitTest {


    IEnvironmentRepository environmentRepository;
    String environment_name = "Balvanera";
    String environment_name_2 = "Balvanera";
    String environment_name_3 = "Barracas";


    @BeforeEach
    @AfterEach
    private void setUp() throws DataBaseNotFoundException {
        this.environmentRepository = new EnvironmentRepository();
    }

    @Test
    @DisplayName("Unit Test - method: findEnvironment - 1")
    void findEnvironment_1() {
        // arrange
        Optional<EnvironmentModel> expected = Optional.of(new EnvironmentModel(environment_name));

        // act
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment(environment_name);

        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: findEnvironment - 2")
    void findEnvironment_2() {
        // arrange
        Optional<EnvironmentModel> expected = Optional.empty();

        // act
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment("NotFound");

        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: findEnvironment - 3")
    void findEnvironment_3() {
        // arrange
        Optional<EnvironmentModel> expected = Optional.of(new EnvironmentModel(environment_name_2));

        // act
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment(environment_name_2);

        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: findEnvironment - 4")
    void findEnvironment_4() {
        // arrange
        Optional<EnvironmentModel> expected = Optional.of(new EnvironmentModel(environment_name_3));

        // act
        Optional<EnvironmentModel> current = environmentRepository.findEnvironment(environment_name_3);

        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: mapDataBase - 1")
    void mapDataBase_1() throws IOException, DataBaseNotFoundException {
        // arrange
        List<EnvironmentModel> expected = UtilTest.getData();
        String path = "src/test/java/resources/environments.json";

        // act
        List<EnvironmentModel> current = environmentRepository.mapDataBase(path);
        // assert
        assertEquals(expected, current);
    }

    @Test
    @DisplayName("Unit Test - method: mapDataBase - 1")
    void mapDataBase_2() throws IOException {
        // arrange
        String path = "src/test/java/resources/environment.json";

        assertThrows(DataBaseNotFoundException.class, () -> environmentRepository.mapDataBase(path));
    }


}
