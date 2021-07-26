package com.mercadolibre.squaremeter.intregrations;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mercadolibre.squaremeter.unit.utils.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegrationDistrict {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @AfterEach
    void init() {
        TestUtilsGenerator.initDataBase();

    }

    @Test
    @DisplayName("Test Integration - valid Object response")
    public void testPostReportHouse() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestOk());
        String response = writer(TestUtilsGenerator.getResultOkReport());
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playLoad))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals(response, mvcResult.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("Test Integration - valid Object request - DistrictNotExist")
    public void testPostReportDistrictNotFound() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestNotOk());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playLoad))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("The District not exist"))
                .andReturn();
    }

    @Test
    @DisplayName("Test Integration - valid error request strings")
    public void testPostReportHouseValidDStrings() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestNotValidStrings());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playLoad))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Please check request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value(TestUtilsGenerator.getMessageValidErrorString()))
                .andReturn();
    }


    @Test
    @DisplayName("Test Integration - valid error request doubles")
    public void testPostReportHouseValidDouble() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestNotValidDouble());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playLoad))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Please check request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value(TestUtilsGenerator.getMessageValidErrorDouble()))
                .andReturn();
    }


    @Test
    @DisplayName("Test Integration - valid error request attributes environments and large strings ")
    public void testPostReportHouseValidLongAndEnvironmentAttributes() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestNotValidStringsLargeAndAttributesEnvironments());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playLoad))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Please check request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value(TestUtilsGenerator.getMessageValidErrorLargeStringAndAttributesEnvironments()))
                .andReturn();
    }


    @Test
    @DisplayName("Test Integration - valid error request attributes not nulls")
    public void testPostReportHouseValidNotNulls() throws Exception {
        String playLoad = writer(TestUtilsGenerator.getRequestNullsAttributes());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/district/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(playLoad))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Please check request"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").
                        value(TestUtilsGenerator.getMessageValidErrorNotNulls()))
                .andReturn();
    }

    private String writer(Object c) throws Exception {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();
        return writer.writeValueAsString(c);

    }


}
