package com.desafio.tucasitatasaciones.unit;

import com.desafio.tucasitatasaciones.dto.PropertyRequestDTO;
import com.desafio.tucasitatasaciones.dto.PropertyResponseDTO;
import com.desafio.tucasitatasaciones.exception.DistrictNotFoundException;
import com.desafio.tucasitatasaciones.model.District;
import com.desafio.tucasitatasaciones.model.Environment;
import com.desafio.tucasitatasaciones.repository.DistrictRepository;
import com.desafio.tucasitatasaciones.service.DistrictService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing district service")
public class DistrictServiceTest {
    @Mock
    DistrictRepository districtRepository;

    @InjectMocks
    DistrictService districtService;

    private PropertyRequestDTO getRequest(){
        Environment env1 = new Environment("Cuarto", 20, 20);
        Environment env2 = new Environment("Cocina", 10, 5);
        List<Environment> envs = new ArrayList<>();
        envs.add(env1);
        envs.add(env2);
        return new PropertyRequestDTO("Hogar Dulce Hogar Dulce Hogar", "Alamos", 5, envs);
    }

    @Test
    @DisplayName("Calculates property area.")
    void rightTotalArea() throws DistrictNotFoundException {
        PropertyRequestDTO requestDTO = getRequest();

        District alamos = new District("Alamos");
        Optional<District> district = Optional.of(alamos);
        Mockito.when(districtRepository.findDistrictByName("Alamos")).thenReturn(district);

        PropertyResponseDTO response = districtService.propertyInfo(requestDTO);

        Mockito.verify(districtRepository, Mockito.atLeastOnce()).findDistrictByName("Alamos");

        Assertions.assertEquals(2250, response.getProp_price());
    }

    @Test
    @DisplayName("Finds the district.")
    void districtFound() throws DistrictNotFoundException{
        PropertyRequestDTO requestDTO = getRequest();

        District alamos = new District("Alamos");
        Optional<District> district = Optional.of(alamos);
        Mockito.when(districtRepository.findDistrictByName("Alamos")).thenReturn(district);

        PropertyResponseDTO response = districtService.propertyInfo(requestDTO);

        Mockito.verify(districtRepository, Mockito.atLeastOnce()).findDistrictByName("Alamos");

        Assertions.assertEquals("Alamos", response.getDistrict_name());
    }

    @Test
    @DisplayName("Throws exception when district is not found.")
    void districtNotFound() throws DistrictNotFoundException{
        PropertyRequestDTO requestDTO = getRequest();

        District alamos = null;
        Optional<District> district = Optional.ofNullable(alamos);
        Mockito.when(districtRepository.findDistrictByName("Alamos")).thenReturn(district);

        Assertions.assertThrows(DistrictNotFoundException.class, () -> districtService.propertyInfo(requestDTO));
    }

    @Test
    @DisplayName("Returns biggest environment.")
    void biggestEnvironment() throws DistrictNotFoundException{
        PropertyRequestDTO requestDTO = getRequest();

        District alamos = new District("Alamos");
        Optional<District> district = Optional.of(alamos);
        Mockito.when(districtRepository.findDistrictByName("Alamos")).thenReturn(district);

        PropertyResponseDTO response = districtService.propertyInfo(requestDTO);

        Mockito.verify(districtRepository, Mockito.atLeastOnce()).findDistrictByName("Alamos");

        Assertions.assertEquals("Cuarto", response.getBiggest_environment());
    }

    @Test
    @DisplayName("Returns environment areas.")
    void environmentAreas() throws DistrictNotFoundException{
        PropertyRequestDTO requestDTO = getRequest();

        District alamos = new District("Alamos");
        Optional<District> district = Optional.of(alamos);
        Mockito.when(districtRepository.findDistrictByName("Alamos")).thenReturn(district);

        PropertyResponseDTO response = districtService.propertyInfo(requestDTO);

        Mockito.verify(districtRepository, Mockito.atLeastOnce()).findDistrictByName("Alamos");

        Assertions.assertAll(
                () -> Assertions.assertEquals(400, response.getEnvironments().get(0).getEnvironment_area()),
                () -> Assertions.assertEquals(50, response.getEnvironments().get(1).getEnvironment_area())
        );
    }

}
