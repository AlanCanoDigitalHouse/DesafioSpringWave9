package com.mercadolibre.tucasitatasaciones.unit.services;

import com.mercadolibre.tucasitatasaciones.dtos.req.EnvironmentDTO;
import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.DistrictServiceImpl;
import com.mercadolibre.tucasitatasaciones.services.AssessmentServiceImpl;
import com.mercadolibre.tucasitatasaciones.unit.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith({MockitoExtension.class})
class AssessmentServiceImplTest {

    @Mock
    private DistrictServiceImpl districtService;

    @InjectMocks
    private AssessmentServiceImpl propertyAssessmentService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCalcDimensionM2() throws DistrictNotFoundException {
        PropertyDTO property = TestUtils.createProperty();

        AssessmentDTO current = propertyAssessmentService.calcDimensionM2(property);

        Double expected = 245D;
        Assertions.assertEquals(expected, current.getDimension());
    }

    @Test
    void testBiggestRoom() throws DistrictNotFoundException {
        PropertyDTO property = TestUtils.createProperty();

        AssessmentDTO current = propertyAssessmentService.getBiggestRoom(property);

        String expected = "Main Bedroom";
        Assertions.assertEquals(expected, current.getBiggestRoom().getEnvironmentName());
    }

    @Test
    void testRoomsDimension() throws DistrictNotFoundException {
        PropertyDTO property = TestUtils.createProperty();

        AssessmentDTO assessment = propertyAssessmentService.getRoomsDimensions(property);

        List<Double> expected = Arrays.asList(48D, 96D, 30D, 30D, 20D, 10.5D, 10.5D);
        List<Double> current = assessment.getRoomsDimensions().stream().map(EnvironmentDTO::getDimension).collect(Collectors.toList());
        Assertions.assertEquals(expected, current);
    }

    @Test
    void testPropPrice() throws DistrictNotFoundException {
        PropertyDTO property = TestUtils.createProperty();

        AssessmentDTO assessment = propertyAssessmentService.calcPrice(property);

        Double expected = 245000D;
        Assertions.assertEquals(expected, assessment.getPrice());
    }

}