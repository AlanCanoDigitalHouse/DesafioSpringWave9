package com.meli.tucasita.unit.service;

import com.meli.tucasita.InfoTestGenerator;
import com.meli.tucasita.dto.Distrito;
import com.meli.tucasita.dto.request.CasaRequestDTO;
import com.meli.tucasita.dto.request.Habitacion;
import com.meli.tucasita.dto.response.CasaResponseDTO;
import com.meli.tucasita.exception.DataBaseException;
import com.meli.tucasita.exception.DiferentDistrictPriceException;
import com.meli.tucasita.exception.NoDistrictFoundException;
import com.meli.tucasita.repository.CasaRepository;
import com.meli.tucasita.service.CasaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit Tet - Tu casita Service functions Test")
public class TuCasitaServiceTest {

    @Mock
    CasaRepository casaRepository;

    @InjectMocks
    private CasaService casaService;

    @Test
    @DisplayName("Area de los Ambientes")
    void calcularAreaHabitacionesTest(){
        CasaRequestDTO casaRequestDTO = InfoTestGenerator.generatesCorrectTestInfo();
        Map<String, Double> habitaciones = InfoTestGenerator.generatesCorrectTestArea();
        Assertions.assertEquals(habitaciones, casaService.calcularAreaHabitaciones(casaRequestDTO));
    }

    @Test
    @DisplayName("Ambiente mas grande")
    void obtenerHabitacionMasGrandeTest(){
        Map<String, Double> habitaciones = InfoTestGenerator.generatesCorrectTestArea();
        String habitacion = "Kitchen";

        Assertions.assertEquals(habitacion, casaService.obtenerHabitacionMasGrande(habitaciones));
    }

    @Test
    @DisplayName("Medida final de la casa")
    void obtenerTamanoCasa(){
        CasaRequestDTO casaRequestDTO = InfoTestGenerator.generatesCorrectTestInfo();
        List<Habitacion> habitacionList=casaRequestDTO.getHabitacion();
        Map<String, Double> habitaciones=InfoTestGenerator.generatesCorrectTestArea();

        Assertions.assertEquals(115.0, casaService.obtenerTamanoCasa(habitacionList,habitaciones));

    }

    @Test
    @DisplayName("Realizar los calculos de la propiedad")
    void calcularMetroPropiedadTest()throws NoDistrictFoundException, DataBaseException, DiferentDistrictPriceException {
        CasaRequestDTO casaRequestDTO = InfoTestGenerator.generatesCorrectTestInfo();
        Map<String, Double> habitaciones = InfoTestGenerator.generatesCorrectTestArea();

        //Mock
        Distrito treceMock = new Distrito("Trece",2500.0);
        when(casaRepository.obtenerDistrito("Trece")).thenReturn(treceMock);

        ResponseEntity<CasaResponseDTO> casaResponseDTOResponseEntity = casaService.calcularMetroPropiedad(casaRequestDTO);

        ResponseEntity<CasaResponseDTO> expected = new ResponseEntity<CasaResponseDTO>(
                new CasaResponseDTO(115.0, 287500.0, habitaciones, "Kitchen"),OK);

        verify(casaRepository, atLeast(1)).obtenerDistrito(anyString());
        Assertions.assertEquals(expected,casaResponseDTOResponseEntity);
    }

    @Test
    @DisplayName("Diferent district price")
    void DiferentDistrictPrice() throws NoDistrictFoundException, DataBaseException {
        //arrange
        CasaRequestDTO casaRequestDTO = InfoTestGenerator.generatesCorrectTestInfo();
        casaRequestDTO.setDistrict_price(1500.0);

        Distrito treceMock = new Distrito("Trece",2500.0);
        when(casaRepository.obtenerDistrito("Trece")).thenReturn(treceMock);

        //act && assert
        Assertions.assertThrows(DiferentDistrictPriceException.class, () -> casaService.calcularMetroPropiedad(casaRequestDTO));
    }
}
