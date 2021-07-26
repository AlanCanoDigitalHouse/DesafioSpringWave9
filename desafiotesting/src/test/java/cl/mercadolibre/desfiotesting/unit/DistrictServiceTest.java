package cl.mercadolibre.desfiotesting.unit;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;
import cl.mercadolibre.desfiotesting.DTOs.EnvironmentDTO;
import cl.mercadolibre.desfiotesting.DTOs.EstateDTO;
import cl.mercadolibre.desfiotesting.DesfioTestingApplication;
import cl.mercadolibre.desfiotesting.repositories.RepositoryDistrictImpl;
import cl.mercadolibre.desfiotesting.services.DistrictServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistrictServiceTest {

    @InjectMocks
    private DistrictServiceImpl DistrictService;

    @Mock
    private RepositoryDistrictImpl repositoryDistrict;

    @Test
    void existsDistrictByDTO(){

        DistrictDTO districtDTOExpected = new DistrictDTO();
        districtDTOExpected.setName("Alerce");
        districtDTOExpected.setPrice(1000.0);

        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setName("Alerce");
        districtDTO.setPrice(1000.0);

        when(repositoryDistrict.findDistrictByDTO(districtDTO)).thenReturn(districtDTOExpected);

        Assertions.assertEquals(true, DistrictService.existsDistrictByDTO(districtDTO));
    }


    private EstateDTO getEstate(){
        DistrictDTO districtDTO = new DistrictDTO();
        districtDTO.setName("Alerce");
        districtDTO.setPrice(1000.0);

        List<EnvironmentDTO> environmentDTOList = new ArrayList<>();
        EnvironmentDTO environmentDTO = new EnvironmentDTO();

        environmentDTO.setName("env1");
        environmentDTO.setLength(10.0);
        environmentDTO.setWidth(10.0);

        environmentDTOList.add(environmentDTO);

        EstateDTO estateDTO = new EstateDTO();
        estateDTO.setName("Puerto Montt");
        estateDTO.setDistrictDTO(districtDTO);
        estateDTO.setEnvironmentDTOList(environmentDTOList);

        return estateDTO;
    }
}
