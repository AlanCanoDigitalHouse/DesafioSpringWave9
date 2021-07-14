package com.meli.socialmeli.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.socialmeli.dto.ProductDTO;
import com.meli.socialmeli.dto.request.NewPostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerNewPostTest {

  String GET_POST_OF_SELLERS_FOLLOWED_BY_INVALID_USER = "/products/followed/10/list";
  String GET_POST_OF_SELLERS_FOLLOWED_BY_VALID_USER = "/products/followed/1/list";
  String GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_ASC = "/products/followed/1/list?order=date_asc";
  String GET_POST_OF_SELLERS_FOLLOWED_BY_ORDER_DESC = "/products/followed/1/list?order=date_desc";
  String NEW_POST = "/products/newpost";
  String NEW_PROMO_POST = "/products/newpromopost";
  String GET_PROMO_POST_COUNT = "/products/2/countPromo";
  String GET_PROMO_POSTS = "/products/2/list";
  NewPostRequest newPostRequest;
  NewPostRequest newInvalidPostRequest;
  NewPostRequest newInvalidPromoRequest;
  NewPostRequest newValidRequestWithInvalidUserId;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    ProductDTO productDTO = ProductDTO.builder()
            .productName("Teclado Gamer")
            .type("Gamer")
            .brand("Alienware")
            .color("Azul")
            .notes("Standard Edition")
            .build();
    newPostRequest = new NewPostRequest(2, LocalDate.now().minusDays(5), productDTO, 100, 2000.0, true, .15);
    newValidRequestWithInvalidUserId = new NewPostRequest(10, LocalDate.now().minusDays(5), productDTO, 100, 2000.0,
            true, .15);
    newInvalidPostRequest = new NewPostRequest(10, LocalDate.now().plusDays(5), productDTO, 0, 0.0, null, null);
    newInvalidPromoRequest = new NewPostRequest(1, LocalDate.now(), productDTO, 100, 140.0, false, 0.0);
  }

  @Test
  void newPostInvalid() throws Exception {
    mockMvc.perform(post(NEW_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newInvalidPostRequest)))
            .andExpect(status().isBadRequest())
            .andDo(print());
  }

  @Test
  void newPostInvalidUser() throws Exception {
    mockMvc.perform(post(NEW_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newValidRequestWithInvalidUserId)))
            .andExpect(status().isBadRequest())
            .andDo(print());
  }

  @Test
  void getPostOfSellersFollowedByInvalidUser() throws Exception {
    mockMvc.perform(get(GET_POST_OF_SELLERS_FOLLOWED_BY_INVALID_USER))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void getPostOfSellersFollowedByValidUser() throws Exception {
    mockMvc.perform(get(GET_POST_OF_SELLERS_FOLLOWED_BY_VALID_USER))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void newInvalidPromoRequest() throws Exception {
    mockMvc.perform(post(NEW_PROMO_POST)
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newInvalidPromoRequest)))
            .andExpect(status().isBadRequest())
            .andDo(print());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}

