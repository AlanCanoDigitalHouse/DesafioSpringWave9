package com.example.desafiotesting.repository;

import com.example.desafiotesting.exception.DistrictNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DistrictRepositoryTest {

    @InjectMocks
    DistrictRepository districtRepository;

    @Test
    public void propertyExistsTest() throws DistrictNotFoundException {
        String prop_name = "Recoleta";

        boolean exists = districtRepository.propertyExists(prop_name);

        Assertions.assertTrue(exists, "El nombre de la propiedad existe");
    }

    @Test()
    public void propertyNoExistsTest(){
        String prop_name = "NO EXISTS";

        Assertions.assertThrows(DistrictNotFoundException.class,
                () -> districtRepository.propertyExists(prop_name)
        );
    }
}
