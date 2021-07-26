package cl.mercadolibre.desfiotesting.unit;

import cl.mercadolibre.desfiotesting.DTOs.DistrictDTO;
import cl.mercadolibre.desfiotesting.DTOs.EnvironmentDTO;
import cl.mercadolibre.desfiotesting.DTOs.EstateDTO;
import cl.mercadolibre.desfiotesting.DTOs.responseDTOs.EnvironmentWithSize;
import cl.mercadolibre.desfiotesting.exceptions.DistrictNotFoundException;
import cl.mercadolibre.desfiotesting.services.DistrictServiceImpl;
import cl.mercadolibre.desfiotesting.services.EstateServiceImpl;
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
public class EstateServiceTest {


    @Mock
    private DistrictServiceImpl DistrictService;

    @InjectMocks
    private EstateServiceImpl EstateService;

    @Test
    void testCalculateEstateSize(){
        try {
            when(DistrictService.existsDistrictByDTO(getEstate().getDistrictDTO())).thenReturn(true);
            Double expected = 100.0;
            Double size = EstateService.calculateEstateSize(getEstate());
            Assertions.assertEquals(expected, size);
        } catch (DistrictNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCalculateEstateSizeEstateNull(){
        when(DistrictService.existsDistrictByDTO(getEstate().getDistrictDTO())).thenReturn(false);
        Assertions.assertThrows(DistrictNotFoundException.class, () ->{
            EstateService.calculateEstateSize(getEstate());
        });
    }

    @Test
    void testCalculateEstatePrice(){
        try {
            when(DistrictService.existsDistrictByDTO(getEstate().getDistrictDTO())).thenReturn(true);
            Double expected = 100000.0;
            Double size = EstateService.calculateEstatePrice(getEstate());
            Assertions.assertEquals(expected, size);
        } catch (DistrictNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCalculateBiggestEnvironment(){
        EstateDTO estateDTO = getEstate();

        EnvironmentDTO environmentBig = new EnvironmentDTO();
        environmentBig.setName("env2");
        environmentBig.setLength(20.0);
        environmentBig.setWidth(20.0);

        estateDTO.getEnvironmentDTOList().add(environmentBig);

        EnvironmentDTO environment = EstateService.calculateBiggestEnvironment(estateDTO);
        Assertions.assertEquals(environmentBig, environment);
    }

    @Test
    void testCalculateSizeOfEachEnvironment(){
        EstateDTO estateDTO = getEstate();
        List<EnvironmentWithSize> environments = new ArrayList<>();
        EnvironmentWithSize environment1 = new EnvironmentWithSize();
        environment1.setName("env1"); environment1.setSize(100.0);
        environments.add(environment1);

        Assertions.assertEquals(environments, EstateService.calculateSizeOfEachEnvironment(estateDTO));
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
