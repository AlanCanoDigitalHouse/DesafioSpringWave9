package com.mercadolibre.calculadorametroscuadrados.unit;

import com.mercadolibre.calculadorametroscuadrados.TestUtils;
import com.mercadolibre.calculadorametroscuadrados.entities.District;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DataBaseNotAvaibleException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepository;
import com.mercadolibre.calculadorametroscuadrados.repository.DistrictRepositoryImpl;
import com.mercadolibre.calculadorametroscuadrados.repository.MyDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Repository - unit test")
@ExtendWith(MockitoExtension.class)
public class RepositoryTest {

    @Mock
    MyDataSource dataSource;

    @InjectMocks
    DistrictRepositoryImpl repository;

    @DisplayName("getting list of districts")
    @Test
    public void getAllDistricts() throws IOException {
        when(dataSource.loadDB()).thenReturn(TestUtils.getExampleList());
        List<District> actual = repository.getAllDistricts();
        List<District> expected = TestUtils.getExampleList();
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("getting price for a valid district ")
    @Test
    public void getPriceByDistrict() throws IOException {
        when(dataSource.loadDB()).thenReturn(TestUtils.getExampleList());
        double actualPrice = repository.getPriceByDistrict("Palermo");
        double expectedPrice = 3900;
        assertEquals(expectedPrice, actualPrice);
    }

    @DisplayName("should throw DistrictNotFoundException ")
    @Test
    public void getPriceForNonExistingDistrict() throws IOException {
        when(dataSource.loadDB()).thenReturn(TestUtils.getExampleList());
        assertThrows(DistrictNotFoundException.class, () -> repository.getPriceByDistrict("non existing district"));
    }

    @DisplayName("should throw DatabaseNoAvailableException ")
    @Test
    public void throwExceptionWhenCantConnectToDB() throws IOException {
        when(dataSource.loadDB()).thenThrow(IOException.class);
        assertThrows(DataBaseNotAvaibleException.class, () -> repository.getPriceByDistrict("Palermo"));
    }




}
