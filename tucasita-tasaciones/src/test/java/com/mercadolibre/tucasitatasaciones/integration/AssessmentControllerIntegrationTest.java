package com.mercadolibre.tucasitatasaciones.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.tucasitatasaciones.dtos.req.PropertyDTO;
import com.mercadolibre.tucasitatasaciones.dtos.res.AssessmentDTO;
import com.mercadolibre.tucasitatasaciones.exception.DistrictNotFoundException;
import com.mercadolibre.tucasitatasaciones.exception.ErrorMessage;
import com.mercadolibre.tucasitatasaciones.unit.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class AssessmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectWriter getWriter() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
    }

    @Test
    void calcPropDimension() throws Exception {
        PropertyDTO request = TestUtils.createProperty();
        AssessmentDTO expected = AssessmentDTO.builder()
                .propName("Porsche Design Tower - 10th F")
                .districtName("Sunny Isles Beach")
                .dimension(245D).build();

        String requestJSON = getWriter().writeValueAsString(request);
        String expectedJSON = getWriter().writeValueAsString(expected);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/property/dimension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testThrowValidationException() throws Exception {
        PropertyDTO request = TestUtils.createProperty();
        request.setPropName("");
        ErrorMessage expected = TestUtils.createValidationErrorForNullPropName();

        String requestJSON = getWriter().writeValueAsString(request);
        String expectedJSON = getWriter().writeValueAsString(expected);

        MvcResult mvcResult= this.mockMvc.perform(MockMvcRequestBuilders.post("/property/dimension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void testThrowPayloadMalformedException() throws Exception {
        ErrorMessage expected = TestUtils.createValidationErrorMalformedPayload();

        String requestJSON = "{'prop_name': \"Prop 01\"}";
        String expectedJSON = getWriter().writeValueAsString(expected);

        MvcResult mvcResult= this.mockMvc.perform(MockMvcRequestBuilders.post("/property/dimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    void testThrowDistrictNotFoundException() throws Exception {
        PropertyDTO request = TestUtils.createProperty();
        request.getDistrict().setDistrictName("NON EXISTING DISTRICT");
        ErrorMessage expected = new ErrorMessage(400, new DistrictNotFoundException(request.getDistrict().getDistrictName()));

        String requestJSON = getWriter().writeValueAsString(request);
        String expectedJSON = getWriter().writeValueAsString(expected);

        MvcResult mvcResult = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/property/dimension")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Assertions.assertEquals(expectedJSON, mvcResult.getResponse().getContentAsString());

    }

}
