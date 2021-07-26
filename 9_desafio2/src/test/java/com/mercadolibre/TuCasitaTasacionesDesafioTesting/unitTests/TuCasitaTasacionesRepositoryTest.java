package com.mercadolibre.TuCasitaTasacionesDesafioTesting.unitTests;

import com.mercadolibre.TuCasitaTasacionesDesafioTesting.entity.District;
import com.mercadolibre.TuCasitaTasacionesDesafioTesting.repositories.TuCasitaTasacionesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

@DisplayName("Unit test - Tu Casita Tasaciones repository")

public class TuCasitaTasacionesRepositoryTest {

    TuCasitaTasacionesRepository repository = new TuCasitaTasacionesRepository();

    @Test
    @DisplayName("Method Found price without mock - correct case")

    public void shouldFindPrice() {
        String location = "Recoleta";
        District locationExpected = new District("Recoleta", 3500.0);
        District currentLocation = repository.findPriceByLocation(location).orElse(null);
        Assertions.assertEquals(locationExpected, currentLocation);
    }

    @Test
    @DisplayName("Method Found price without mock - incorrect case")

    public void shouldNotFindPrice() {
        String district = "Caballito";
        District districtExpected = new District("Recoleta", 3500.0);
        District currentDistrict = repository.findPriceByLocation(district).orElse(null);
        Assertions.assertNotEquals(districtExpected, currentDistrict);

    }
    @Test
    @DisplayName("Method load data base - Repository test -  correct case")

    public void shouldLoadDataBase() {
        ArrayList<District> expectedList = createDistrictList();
        ArrayList<District> currentList = repository.loadDatabase();
        Assertions.assertEquals(expectedList, currentList);
    }

    @Test
    @DisplayName("Method load data base - Repository test -  incorrect case")

    public void shouldNotLoadDataBase() {
        ArrayList<District> expectedList = createDistrictList();
        District expectedDistrict = new District("Villa Urquiza", 2500.0);
        expectedList.add(expectedDistrict);
        ArrayList<District> currentList = repository.loadDatabase();
        Assertions.assertNotEquals(expectedList, currentList);
    }

    /**
     * Create a district list
     */
    private ArrayList<District> createDistrictList() {
        return new ArrayList<>() {{
            add(new District("Palermo", 3000.0));
            add(new District("Belgrano", 3100.0));
            add(new District("Recoleta", 3500.0));
            add(new District("Puerto Madero", 2600.0));
            add(new District("Caballito", 2000.0));
            add(new District("Villa Crespo", 1700.0));
            add(new District("Villa del Parque", 1500.0));
        }};
    }
}
