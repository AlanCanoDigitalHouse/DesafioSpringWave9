package com.example.tucasitatasaciones.repositories;

import com.example.tucasitatasaciones.dtos.DistrictDto;
import com.example.tucasitatasaciones.dtos.requests.HomeRequestDto;
import com.example.tucasitatasaciones.exceptions.GetDistrictException;
import com.example.tucasitatasaciones.repository.DistrictRepository;
import com.example.tucasitatasaciones.repository.IDistrictRepository;
import com.example.tucasitatasaciones.utils.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DistrictRepositoryTests {
    IDistrictRepository repository;

    @BeforeEach
    @AfterEach
    private void setUp() {
        this.repository = new DistrictRepository();
    }

    @Test
    public void findExistentDistrict() {
        // arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home Repo");

        // act
        DistrictDto district = repository.findDistrict(home.getDistrict().getName(), home.getDistrict().getPrice());

        // assert
        Assertions.assertEquals(district.getPrice(), home.getDistrict().getPrice());
    }

    @Test
    public void notFindExistentDistrict() throws GetDistrictException {
        // arrange
        HomeRequestDto home = TestUtilsGenerator.getHomeWith3Rooms("Home Repo");

        // assert
        assertThrows(GetDistrictException.class, () -> repository.findDistrict(home.getDistrict().getName(),10.0));
    }
}
