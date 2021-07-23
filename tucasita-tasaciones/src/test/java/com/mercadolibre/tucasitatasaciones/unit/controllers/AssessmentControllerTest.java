package com.mercadolibre.tucasitatasaciones.unit.controllers;

import com.mercadolibre.tucasitatasaciones.controllers.AssessmentController;
import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.services.AssessmentServiceImpl;
import com.mercadolibre.tucasitatasaciones.unit.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith({MockitoExtension.class})
class AssessmentControllerTest {

    @Mock
    private AssessmentServiceImpl assessmentService;

    @InjectMocks
    private AssessmentController controller;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calcPropDimension() throws DistrictNotFoundException {
        // arrange
        PropertyDTO prop = TestUtils.createProperty();
        AssessmentDTO expected = AssessmentDTO.builder().dimension(245D).build();
        Mockito.when(assessmentService.calcDimensionM2(prop)).thenReturn(expected);

        // act
        ResponseEntity<AssessmentDTO> result = controller.calcPropDimension(prop);

        // assert
        Mockito.verify(assessmentService, Mockito.atLeastOnce()).calcDimensionM2(prop);
        Assertions.assertEquals(expected, result.getBody());
    }

    @Test
    void calcPropPrice() throws DistrictNotFoundException {
        // arrange
        PropertyDTO prop = TestUtils.createProperty();
        AssessmentDTO expected = AssessmentDTO.builder().price(245000D).build();
        Mockito.when(assessmentService.calcPrice(prop)).thenReturn(expected);

        // act
        ResponseEntity<AssessmentDTO> result = controller.calcPropPrice(prop);

        // assert
        Mockito.verify(assessmentService, Mockito.atLeastOnce()).calcPrice(prop);
        Assertions.assertEquals(expected, result.getBody());
    }

    @Test
    void getBiggestRoom() throws DistrictNotFoundException {
        // arrange
        PropertyDTO prop = TestUtils.createProperty();
        AssessmentDTO expected = AssessmentDTO.builder().biggestRoom(TestUtils.createBiggestRoom()).build();
        Mockito.when(assessmentService.getBiggestRoom(prop)).thenReturn(expected);

        // act
        ResponseEntity<AssessmentDTO> result = controller.getBiggestRoom(prop);

        // assert
        Mockito.verify(assessmentService, Mockito.atLeastOnce()).getBiggestRoom(prop);
        Assertions.assertEquals(expected, result.getBody());
    }

    @Test
    void getRoomsDimensions() throws DistrictNotFoundException {
        // arrange
        PropertyDTO prop = TestUtils.createProperty();
        AssessmentDTO expected = AssessmentDTO.builder().roomsDimensions(prop.getEnvironments()).build();
        Mockito.when(assessmentService.getRoomsDimensions(prop)).thenReturn(expected);

        // act
        ResponseEntity<AssessmentDTO> result = controller.getRoomsDimensions(prop);

        // assert
        Mockito.verify(assessmentService, Mockito.atLeastOnce()).getRoomsDimensions(prop);
        Assertions.assertEquals(expected, result.getBody());
    }

}