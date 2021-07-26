package com.mercadolibre.tucasitatasaciones.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dto.response.ErrorDTO;
import com.mercadolibre.tucasitatasaciones.exception.DatabaseException;
import com.mercadolibre.tucasitatasaciones.repository.DistrictRepository;
import com.mercadolibre.tucasitatasaciones.util.JSONDataUtil;
import com.mercadolibre.tucasitatasaciones.util.TestDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionControllerTestWithMock {

    @Autowired
    private MockMvc mockMvc;
    private static final String DATA_DIR = "classpath:static/districts.json";

    /*
    * These tests are intended to easily simulate runtime errors and test that they are handled correctly.
    * They are on a different class to avoid errors on tests that don't need a mocked bean.
    */
    @MockBean
    DistrictRepository mockRepository;

    @BeforeEach
    void initData() {
        JSONDataUtil.cleanData(DATA_DIR);
        TestDataUtil.initDummyData(DATA_DIR);
    }

    private ObjectWriter getObjectWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    @DisplayName(value = "Test correct error response is returned when there is a database error")
    void testDatabaseException() throws Exception {
        var objectWriter = this.getObjectWriter();
        var propertyData = TestDataUtil.getPropertyFromDistrict("DistrictA");

        var requestBody = objectWriter.writeValueAsString(propertyData);

        var expectedStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        var expectedExceptionMessage = "There was an error in the Database";
        var expectedBody = objectWriter
                .writeValueAsString(new ErrorDTO(expectedStatus, expectedExceptionMessage));

        Mockito.when(mockRepository.getDistrictByName("DistrictA")).thenThrow(new DatabaseException(expectedExceptionMessage));

        var mvcResult = this.mockMvc.perform(post("/property/valuation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        Mockito.verify(mockRepository, Mockito.atLeastOnce()).getDistrictByName("DistrictA");
        Assertions.assertAll(
                () -> Assertions.assertEquals(mvcResult.getResponse().getContentAsString(), expectedBody),
                () -> Assertions.assertEquals(mvcResult.getResponse().getStatus(), expectedStatus)
        );
    }

}
