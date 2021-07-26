package com.mercadolibre.calculadorametroscuadrados.unit.repositories;

import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repositories.DistrictsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisctrictsRepositoryTest {

    DistrictsRepository repository = new DistrictsRepository();

    @BeforeEach
    public void load(){
        repository.loadDistricts();
    }

    @Test
    @DisplayName("Verificar que el barrio de entrada exista en el repositorio de barrios.")
    public void verifyDistrict(){
        // Start
        String find = "Centro";

        // Act
        var aux = repository.getDistrict(find);

        // Assert
        assertTrue(aux);
    }

    @Test
    @DisplayName("Verificar la no existencia de un barrio en el repositorio.")
    public void districtDoesNotExist(){
        // Start
        String find = "Palermo";

        // Act & Assert
        assertThrows(DistrictNotFoundException.class, () -> repository.getDistrict(find));
    }


}
