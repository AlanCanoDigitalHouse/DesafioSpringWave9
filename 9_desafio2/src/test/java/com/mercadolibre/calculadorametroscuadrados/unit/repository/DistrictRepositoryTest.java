package com.mercadolibre.calculadorametroscuadrados.unit.repository;

import com.mercadolibre.calculadorametroscuadrados.exception.repository.AlreadyExistException;
import com.mercadolibre.calculadorametroscuadrados.exception.repository.NotFoundException;
import com.mercadolibre.calculadorametroscuadrados.model.DistrictDAO;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistrictRepositoryTest {

    private DistrictRepository districtRepository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        this.districtRepository = new DistrictRepositoryImpl();
    }

    @Test
    void shouldFindADistrictByTheirName() {
        DistrictDAO expectedDistrict = new DistrictDAO("Test", 220D);

        DistrictDAO actualDistrict = districtRepository.findByName("Test");

        assertEquals(expectedDistrict, actualDistrict);
    }

    @Test
    void shouldTryToFindADistrictByTheirNameAndThrowAnException() {
        assertThrows(NotFoundException.class, () -> districtRepository.findByName("NotExist"));
    }

    @Test
    void shouldCreateANonExistentDistrict() {
        DistrictDAO district = new DistrictDAO("Test1", 2400D);

        districtRepository.save(district);
        assertEquals(district, districtRepository.findByName(district.getDistrict_name()));

    }

    @Test
    void shouldTryToCreateAExistentDistrictAndThrowException() {
        DistrictDAO district = new DistrictDAO("Test", 2400D);

        assertThrows(AlreadyExistException.class, () -> districtRepository.save(district));
    }

    @Test
    void shouldDeleteAnExistentDistrict() {
        DistrictDAO district = new DistrictDAO("Test", 2400D);

        Boolean result = districtRepository.delete(district.getDistrict_name());
        assertEquals(true, result);
        assertThrows(NotFoundException.class, () -> districtRepository.findByName(district.getDistrict_name()));
    }

    @Test
    void shouldTryToDeleteANonExistentDistrictAndThrowException() {
        DistrictDAO district = new DistrictDAO("NonExistent", 2400D);

        assertThrows(NotFoundException.class, () -> districtRepository.delete(district.getDistrict_name()));
    }

    @Test
    void shouldUpdateAnExistentDistrict() {
        DistrictDAO newDistrict = new DistrictDAO("Test", 4000D);

        DistrictDAO updated = districtRepository.update(newDistrict);

        assertEquals(newDistrict, updated);
        assertEquals(newDistrict, districtRepository.findByName(newDistrict.getDistrict_name()));
    }

    @Test
    void shouldTryToUpdateANonExistentDistrictAndThrowException() {
        DistrictDAO district = new DistrictDAO("NonExistent", 2400D);

        assertThrows(NotFoundException.class, () -> districtRepository.update(district));
    }


}
