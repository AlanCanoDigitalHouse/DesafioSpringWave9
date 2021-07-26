package com.meli.desafioTest.UnitTests;

import com.meli.desafioTest.TestDataUtilities;
import com.meli.desafioTest.CalculateService;
import com.meli.desafioTest.Dtos.DistrictDTO;
import com.meli.desafioTest.Dtos.EnvironmentDTO;
import com.meli.desafioTest.Dtos.HouseDTO;
import com.meli.desafioTest.Dtos.HouseResponseDTO;
import com.meli.desafioTest.exception.DistrictNotFoundException;
import com.meli.desafioTest.exception.DistrictPriceNotMatchException;
import com.meli.desafioTest.repository.IRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceUnitTest {

    @Mock
    IRepository repository;

    @InjectMocks
    CalculateService calculateService;

    //Validate el metodo calculate corra correctamente y utilice el repositorio
    @Test
    public void SuccessCalculateUsingRepository() {

        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(house.getDistrict());
        // act
        calculateService.calculate(house);

        // assert
        verify(repository, atLeastOnce()).getDistrictByName(house.getDistrict().getDistrict_name());
    }

    //Valida que el resultado de metros cuadrados sea el correcto
    @Test
    public void SuccessCalculateWithCorrectTotalSquards() {
        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        AtomicReference<Double> expectResult = new AtomicReference<>(0.0);
        house.getEnvironments().forEach(e -> expectResult.updateAndGet(v -> v + e.getEnvironment_squareMeters()));
        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(house.getDistrict());
        // act
        HouseResponseDTO response = calculateService.calculate(house);

        // assert
        assertEquals(expectResult.get(), response.getSquareMeters());
    }

    //Valida que el resultado del precio sea el correcto
    @Test
    public void SuccessCalculateWithCorrectPrice() {
        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        AtomicReference<Double> meters = new AtomicReference<>(0.0);
        house.getEnvironments().forEach(e -> meters.updateAndGet(v -> v + e.getEnvironment_squareMeters()));
        Double expectResult = meters.get() * house.getDistrict().getDistrict_price();

        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(house.getDistrict());
        // act
        HouseResponseDTO response = calculateService.calculate(house);

        // assert
        assertEquals(expectResult, response.getPrice());
    }

    //Valida que devuelva la habitacion mas grande correctamente
    @Test
    public void SuccessCalculateBiggestRoom() {
        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        EnvironmentDTO expectResult = TestDataUtilities.BiguestRoom(house.getEnvironments());
        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(house.getDistrict());
        // act
        HouseResponseDTO response = calculateService.calculate(house);

        // assert
        assertEquals(expectResult.getEnvironment_name(), response.getBiggest().getEnvironment_name());
    }

    //Valida que cuando el metodo Calculate no encuentre el barrio dispare la excepcion correcta
    @Test
    public void CalculateDistrictNotFound() {
        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(null);

        // assert
        assertThrows(DistrictNotFoundException.class, () -> calculateService.calculate(house));
    }

    //Valida que cuando el metodo Calculate encuentre el barrio pero con un precio diferente
    // dispare la excepcion correcta
    @Test
    public void CalculateDistrictPriceNotMatch() {
        // arrange
        HouseDTO house = TestDataUtilities.getHouseWithAllAndCorrectData();
        when(repository.getDistrictByName(house.getDistrict().getDistrict_name())).thenReturn(new DistrictDTO("", 0.0));

        // assert
        assertThrows(DistrictPriceNotMatchException.class, () -> calculateService.calculate(house));
    }


}
