package com.meli.desafioTest.UnitTests;

import com.meli.desafioTest.TestDataUtilities;
import com.meli.desafioTest.Dtos.HouseDTO;
import com.meli.desafioTest.ICalculateService;
import com.meli.desafioTest.controller.CalculateController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class CalculateControllerUnitTest {
    @Mock
    ICalculateService service;

    @InjectMocks
    CalculateController controller;

    //Valida que el controlador llame correctamente al metodo en el servicio
    @Test
    public void SuccessCalculate() {
        // arrange
        HouseDTO stu = TestDataUtilities.getHouseWithAllAndCorrectData();

        // act
        controller.calculate(stu);

        // assert
        verify(service, atLeastOnce()).calculate(stu);
    }

}
