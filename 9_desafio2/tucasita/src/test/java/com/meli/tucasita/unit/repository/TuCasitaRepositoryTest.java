package com.meli.tucasita.unit.repository;

import com.meli.tucasita.dto.Distrito;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import com.meli.tucasita.repository.CasaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit Tet - Tu casita Repository Test")
public class TuCasitaRepositoryTest {

    CasaRepository casaRepository = new CasaRepository();

    @Test
    @DisplayName("Found Distrito by District Name")
    void foundDistrict() throws NoDistrictFoundException, DataBaseException {
        //arrange
        String districtName = "Trece";
        Distrito expected= new Distrito("Trece",2500.0);
        //act
        Distrito current = casaRepository.obtenerDistrito(districtName);
        //assert
        Assertions.assertAll(
                () ->Assertions.assertEquals(expected.getDistrictName(),current.getDistrictName()),
                () -> Assertions.assertEquals(expected.getPricePerMeter(),current.getPricePerMeter())
        );
    }

    @Test
    @DisplayName("Distrito not found")
    void NotFoundDistrito(){
        //arrange
        String DistrictName= "notFound";

        //act && assert
        Assertions.assertThrows(NoDistrictFoundException.class, () -> casaRepository.obtenerDistrito(DistrictName));
    }

}
