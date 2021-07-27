package com.mercadolibre.tucasita.service;

import com.mercadolibre.tucasita.dto.PropertyDTO;
import com.mercadolibre.tucasita.dto.response.EnvironmentInfoResponseDTO;
import com.mercadolibre.tucasita.dto.response.PropertyInfoResponseDTO;
import com.mercadolibre.tucasita.handler.PropertyInfoHandler;
import com.mercadolibre.tucasita.repositories.PriceRepositoryImpl;
import com.mercadolibre.tucasita.utils.TestGeneratorUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PropertyInfoServiceTest {

    private static PropertyDTO propertyWith1Env;
    private static PropertyDTO propertyWith4Env;
    private static List<EnvironmentInfoResponseDTO> oneEnvInfoList;
    private static List<EnvironmentInfoResponseDTO> fourEnvInfoList;
    @Mock
    PropertyInfoHandler handler;
    @Mock
    PriceRepositoryImpl priceRepository;
    @InjectMocks
    PropertyService service;

    @BeforeAll
    static void setup() {
        propertyWith1Env = TestGeneratorUtils.createPropertyWith1Environment();
        propertyWith4Env = TestGeneratorUtils.createPropertyWith4Environments();

        oneEnvInfoList = Arrays.asList(TestGeneratorUtils.bedroomResponse);
        fourEnvInfoList = Arrays.asList(TestGeneratorUtils.bedroomResponse, TestGeneratorUtils.kitchenResponse,
                TestGeneratorUtils.livingRoomResponse, TestGeneratorUtils.bathroomResponse);
    }

    @Test
    @DisplayName("Given a property dto, when getting info, environment list is ok")
    void getInfoEnvironmentListTest() {
        //arrange
        when(handler.getEnvironmentInfoList(propertyWith1Env.getEnvironments())).thenReturn(oneEnvInfoList);
        when(priceRepository.findPriceLocation(any())).thenReturn(TestGeneratorUtils.recoleta);

        //act
        PropertyInfoResponseDTO response = service.getInfo(propertyWith1Env);

        //assert
        verify(handler, atLeastOnce()).getEnvironmentInfoList(any());
        verify(priceRepository, atLeastOnce()).findPriceLocation(any());
        assertEquals(oneEnvInfoList, response.getEnvironmentList());
    }

    @Test
    @DisplayName("Given a property dto, when getting info, bigger environment matches")
    void getInfoBiggerEnvironmentTest() {
        //arrange
        when(handler.getBiggerEnvironment(any())).thenReturn(TestGeneratorUtils.livingRoomResponse);
        when(priceRepository.findPriceLocation(any())).thenReturn(TestGeneratorUtils.recoleta);

        //act
        PropertyInfoResponseDTO response = service.getInfo(propertyWith1Env);

        //assert
        verify(handler, atLeastOnce()).getBiggerEnvironment(any());
        verify(priceRepository, atLeastOnce()).findPriceLocation(any());
        assertEquals(TestGeneratorUtils.livingRoomResponse, response.getBiggerEnvironment());
    }

    @Test
    @DisplayName("Given a property dto, when getting info, total square feet is ok")
    void getInfoTotalSquareFeetTest() {
        //arrange
        when(handler.calculatePropertyTotalSquareFeet(any()))
                .thenReturn(TestGeneratorUtils.getSumOfAllEnvsSquareFeet());
        when(priceRepository.findPriceLocation(any())).thenReturn(TestGeneratorUtils.palermo);

        //act
        PropertyInfoResponseDTO response = service.getInfo(propertyWith4Env);

        //assert
        verify(handler, atLeastOnce()).calculatePropertyTotalSquareFeet(any());
        verify(priceRepository, atLeastOnce()).findPriceLocation(any());
        assertEquals(TestGeneratorUtils.getSumOfAllEnvsSquareFeet(), response.getTotalSquareFeet());
    }

    @Test
    @DisplayName("Given a property dto, when getting info, total price is ok")
    void getInfoTotalPriceTest() {
        //arrange
        when(handler.getEnvironmentInfoList(propertyWith4Env.getEnvironments())).thenReturn(fourEnvInfoList);
        when(handler.calculatePropertyTotalSquareFeet(any()))
                .thenReturn(TestGeneratorUtils.getSumOfAllEnvsSquareFeet());
        when(priceRepository.findPriceLocation(any())).thenReturn(TestGeneratorUtils.palermo);

        //act
        PropertyInfoResponseDTO response = service.getInfo(propertyWith4Env);

        //assert
        verify(priceRepository, atLeastOnce()).findPriceLocation(any());
        assertEquals(4800D, response.getTotalPrice());
    }


}
