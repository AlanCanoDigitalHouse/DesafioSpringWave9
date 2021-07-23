package com.example.desafiotesting.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PropertyRepositoryTest {

    @InjectMocks
    PropertyRepository propertyRepository;

    @Test
    public void propertyExistsTest(){
        String prop_name = "Recoleta";

        boolean exists = propertyRepository.propertyExists(prop_name);

        Assertions.assertTrue(exists, "El nombre de la propiedad existe");
    }

    @Test
    public void propertyNoExistsTest(){
        String prop_name = "NO EXISTS";

        boolean exists = propertyRepository.propertyExists(prop_name);

        Assertions.assertFalse(exists, "El nombre de la propiedad no existe");
    }
}
