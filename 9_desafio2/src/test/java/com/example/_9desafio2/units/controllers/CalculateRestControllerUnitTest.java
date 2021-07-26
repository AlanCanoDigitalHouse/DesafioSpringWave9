package com.example._9desafio2.units.controllers;

import com.example._9desafio2.controllers.CalculateRestController;
import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.dtos.request.EnviromentDTO;
import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.exceptions.PriceNotMatchException;
import com.example._9desafio2.services.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateRestControllerUnitTest {

    @Mock
    CalculateService service;

    @InjectMocks
    CalculateRestController controller;

    //setup
    PropertyDTO propertyDTO=new PropertyDTO();
    DistrictDTO palermo=new DistrictDTO("Palermo", 1000.0);

    @BeforeEach
    public void setUp(){
        List<EnviromentDTO> enviroments=new ArrayList<>();
        EnviromentDTO kitchen=new EnviromentDTO("Kitchen",24.0,33.0);
        EnviromentDTO dinner=new EnviromentDTO("Dinner",20.0,32.0);
        enviroments.add(kitchen);
        enviroments.add(dinner);
        propertyDTO.setProp_name("Mi casa");
        propertyDTO.setDistrict(palermo);
        propertyDTO.setEnvironments(enviroments);

    }

    @Test
    @DisplayName("Verifica que funcione bien el metodo del controller")
    public void correctCalculateTest() throws DistrictNotFoundException, PriceNotMatchException {

        //act
        controller.calculate(propertyDTO);

        //assert
        //check the moc
        verify(service, atLeastOnce()).calculate(propertyDTO);

    }

}
