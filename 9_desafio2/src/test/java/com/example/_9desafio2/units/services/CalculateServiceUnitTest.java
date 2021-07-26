package com.example._9desafio2.units.services;
import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.dtos.request.EnviromentDTO;
import com.example._9desafio2.dtos.request.PropertyDTO;
import com.example._9desafio2.dtos.response.EnviromentResponseDTO;
import com.example._9desafio2.dtos.response.PropertyResponseDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.exceptions.PriceNotMatchException;
import com.example._9desafio2.repositories.PriceRepository;
import com.example._9desafio2.services.CalculateService;
import org.junit.jupiter.api.Assertions;
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
public class CalculateServiceUnitTest {

    @Mock
    PriceRepository repository;

    @InjectMocks
    CalculateService service;


    //set up
    PropertyDTO propertyDTO=new PropertyDTO();
    List<EnviromentDTO> enviroments=new ArrayList<>();
    DistrictDTO palermo=new DistrictDTO("Palermo", 1000.0);
    EnviromentDTO kitchen=new EnviromentDTO("Kitchen",24.0,33.0);
    EnviromentDTO dinner=new EnviromentDTO("Dinner",20.0,32.0);

    @BeforeEach
    public void setuP(){
        propertyDTO.setProp_name("Mi casa");
        propertyDTO.setDistrict(palermo);
        propertyDTO.setEnvironments(enviroments);
    }

    @Test
    @DisplayName("Calculo correcto de metros cuadrados")
    public void getCorrectSquareMetersTest() throws DistrictNotFoundException, PriceNotMatchException {

        //arrange
        enviroments.add(kitchen);
        enviroments.add(dinner);

        //mock
        DistrictDTO palermoMock=new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict("Palermo")).thenReturn(palermoMock);

        //act
        PropertyResponseDTO propertyResponseDTO =service.calculate(propertyDTO);

        //assert
        verify(repository,atLeastOnce()).findPriceDistrict(propertyDTO.getDistrict().getDistrict_name());
        Assertions.assertEquals( 1432.0 ,propertyResponseDTO.getSquareMeters());

    }


    @Test
    @DisplayName("Calculo correcto de hambiente con mayor tamaño")
    public void getCorrectBiggestRoomTest() throws DistrictNotFoundException, PriceNotMatchException {


        //arrange
        enviroments.add(kitchen);
        enviroments.add(dinner);
        EnviromentDTO expectedBiggest=new EnviromentDTO("Kitchen",24.0,33.0);

        //mock
        DistrictDTO palermoMock=new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict("Palermo")).thenReturn(palermoMock);

        //act
        PropertyResponseDTO propertyResponseDTO =service.calculate(propertyDTO);

        //assert
        verify(repository,atLeastOnce()).findPriceDistrict(palermoMock.getDistrict_name());
        Assertions.assertEquals( expectedBiggest ,propertyResponseDTO.getBiggest());

    }

    @Test
    @DisplayName("El precio ingresado no coincide con el precio en la DB")
    public void priceDistrictNotMatchTest() throws DistrictNotFoundException{

        //arrange
        palermo.setDistrict_price(100.0);
        enviroments.add(kitchen);
        enviroments.add(dinner);


        //mock
        DistrictDTO palermoMock=new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict("Palermo")).thenReturn(palermoMock);

        //act and assert
        Assertions.assertThrows(PriceNotMatchException.class,()-> service.calculate(propertyDTO));

    }

    @Test
    @DisplayName("Los metros cuadrados por ambiente son correctos")
    public void correctSquareMetersPerEnviromentTest() throws DistrictNotFoundException, PriceNotMatchException {

        //arrange
        //expected elements
        List<EnviromentResponseDTO> expectedEnviroments=new ArrayList<>();
        expectedEnviroments.add(new EnviromentResponseDTO("Kitchen",792.0));
        expectedEnviroments.add(new EnviromentResponseDTO("Dinner",640.0));
        enviroments.add(kitchen);
        enviroments.add(dinner);

        //mock
        DistrictDTO palermoMock=new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict("Palermo")).thenReturn(palermoMock);

        //act
        PropertyResponseDTO propertyResponseDTO =service.calculate(propertyDTO);

        //assert
        verify(repository,atLeastOnce()).findPriceDistrict(palermoMock.getDistrict_name());
        Assertions.assertEquals( expectedEnviroments ,propertyResponseDTO.getEnviroments());

    }

    @Test
    @DisplayName("Calculo correcto del precio")
    public void correctPropertyPriceTest() throws DistrictNotFoundException, PriceNotMatchException {

        //arrange
        enviroments.add(kitchen);
        enviroments.add(dinner);


        //mock
        DistrictDTO palermoMock=new DistrictDTO("Palermo", 1000.0);
        when(repository.findPriceDistrict("Palermo")).thenReturn(palermoMock);

        //act
        PropertyResponseDTO propertyResponseDTO =service.calculate(propertyDTO);

        //assert
        verify(repository,atLeastOnce()).findPriceDistrict(palermoMock.getDistrict_name());
        Assertions.assertEquals(1432000.0,propertyResponseDTO.getPrice());

    }




}
