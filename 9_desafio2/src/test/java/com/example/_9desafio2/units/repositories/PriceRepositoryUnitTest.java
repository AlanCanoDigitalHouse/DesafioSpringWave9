package com.example._9desafio2.units.repositories;
import com.example._9desafio2.dtos.request.DistrictDTO;
import com.example._9desafio2.exceptions.DistrictNotFoundException;
import com.example._9desafio2.repositories.IPriceRepository;
import com.example._9desafio2.repositories.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceRepositoryUnitTest {

    IPriceRepository repository=new PriceRepository();


    @Test
    @DisplayName("Verifica que se encuentre el precio correcto")
    public void findDistrict() throws DistrictNotFoundException {
        //arrange
        DistrictDTO palermo=new DistrictDTO();
        palermo.setDistrict_name("Palermo");
        palermo.setDistrict_price(1000.0);

        //act
        DistrictDTO district= repository.findPriceDistrict("Palermo");

        //assert
        Assertions.assertEquals(palermo,district);

    }

    @Test
    @DisplayName("Verifica que no se encuentre el precio correcto")
    public void notFoundDistrict(){

        //assert
        Assertions.assertThrows(DistrictNotFoundException.class,() ->repository.findPriceDistrict("Godoy Cruz"));

    }
}
