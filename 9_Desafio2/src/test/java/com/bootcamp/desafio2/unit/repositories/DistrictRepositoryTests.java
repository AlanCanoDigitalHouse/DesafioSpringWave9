package com.bootcamp.desafio2.unit.repositories;

import com.bootcamp.desafio2.dtos.request.PropertyDto;
import com.bootcamp.desafio2.repositories.IDistrictRepository;
import com.bootcamp.desafio2.utils.TestUtilGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DistrictRepositoryTests {

    @Mock
    IDistrictRepository districtRepository;

    @Test
    @DisplayName("Obtener distrito existente")
    public void getExistentDistrict() throws IOException {
        PropertyDto property = TestUtilGenerator.getProperty("Campo");
        when(districtRepository.districtExist(property.getDistrict().getDistrict_name(),
                property.getDistrict().getDistrict_price())).thenReturn(true);
        boolean exist = districtRepository.districtExist("Belgrano", 1100D);

        Assertions.assertTrue(exist);
    }
    @Test
    @DisplayName("Obtener distrito Inexistente")
    public void getInexistentDistrict() throws IOException {
        boolean exist = districtRepository.districtExist("Palermo", 1100D);
        Assertions.assertFalse(exist);
    }

    @Test
    @DisplayName("Obtener distrito inexistente")
    public void getNotFoundDistrict() throws IOException{
        when(districtRepository.districtExist(anyString(),anyDouble())).thenThrow(IOException.class);
        Assertions.assertThrows(IOException.class, () -> districtRepository.districtExist("Villamiriam",2000D));
    }

}
