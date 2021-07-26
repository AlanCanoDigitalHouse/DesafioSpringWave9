package cl.mercadolibre.desfiotesting.unit;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;
import cl.mercadolibre.desfiotesting.repositories.RepositoryDistrictImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistrictRepositoryTests {


    RepositoryDistrictImpl repositoryDistrict = new RepositoryDistrictImpl();

    @Test
    void testFindDistrictByDTO(){
        DistrictDTO districtExpected = new DistrictDTO();
        districtExpected.setName("Alerce");
        districtExpected.setPrice(1000.0);
        DistrictDTO actual = repositoryDistrict.findDistrictByDTO(districtExpected);
        Assertions.assertEquals(districtExpected,actual);
    }

}
